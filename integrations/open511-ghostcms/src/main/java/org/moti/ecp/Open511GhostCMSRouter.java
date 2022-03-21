package org.moti.ecp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.moti.ecp.aggregators.GhostEventsPageAggregator;
import org.moti.ecp.beans.GhostApiTokenGeneratorBean;
import org.moti.ecp.types.Open511Events;
import org.springframework.stereotype.Component;

/**
 * Camel route to load most recent Open 511 Road events to Drive BC Ghost CMS
 * site.
 */
@Component
public class Open511GhostCMSRouter extends RouteBuilder {

  @Override
  public void configure() {

    from("quartz://open511eventsTimer?cron={{camel.cron}}") // CRON format for every 10 minutes
        .routeId("Camel-Open511-GhostCMS")   
         
        .setHeader(Exchange.HTTP_METHOD, simple("GET"))
        .log("API Call to : http://{{application.open511.apiroot}}/events?bridgeEndpoint=true")
        .toD("http://{{application.open511.apiroot}}/events?bridgeEndpoint=true")
    
        // Set header witha formatted date for use in XML transformation
        .setHeader("current-datetime",  simple ("${date:now:yyyy-MM-dd HH:mm:ss}") )
        .unmarshal(new JacksonDataFormat(Open511Events.class))
        .marshal().jacksonxml(true)
        .to("xslt:open511.xsl") // transform to HTML as this will be used in the Ghost CMS page update
        .removeHeaders("CamelHttp*") // remove any Camel headers because the HTML is being stored in the Ghost CMS Page       
        .enrich("direct:ghost-update", new GhostEventsPageAggregator())         
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken")) //.. likely need to get a new token first but maybe not       
        .setHeader(Exchange.HTTP_METHOD, simple("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.pageid}}" + "?source=html&httpMethod=PUT") // Update Ghost Page with aggregate	-- source=html tells ghost we'll be using html instead of mobiledoc format	
        .end();

    from("direct:ghost-update")
        // Generate token and set header for Ghost CMS authorization
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken"))
        // Get page so we can capture date of last change as this will be required for publishing an update.
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.pageid}}" + "?httpMethod=GET")
        .end();
  }

}
