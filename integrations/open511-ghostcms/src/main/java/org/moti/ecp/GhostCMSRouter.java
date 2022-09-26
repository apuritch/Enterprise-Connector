package org.moti.ecp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.moti.ecp.aggregators.GhostEventsPageAggregator;
import org.moti.ecp.beans.GhostApiTokenGeneratorBean;
import org.moti.ecp.types.Open511Events;
import org.moti.ecp.types.Weather;
import org.springframework.stereotype.Component;

/**
 * Camel route to load most recent Open 511 Road events to Drive BC Ghost CMS
 * site.
 */
@Component
public class GhostCMSRouter extends RouteBuilder {

  @Override
  public void configure() {

    from("quartz://open511eventsTimer?cron={{drivebc-ghostcms.camel.cron}}") // CRON format for every 10 minutes
        .routeId("open511-ghost-cms")   
         
        .setHeader(Exchange.HTTP_METHOD, simple("GET"))
        .log("API Call to : http://{{application.open511.apiroot}}/events?bridgeEndpoint=true")
        .toD("http://{{application.open511.apiroot}}/events?bridgeEndpoint=true")
    
        // Set header witha formatted date for use in XML transformation
        .setHeader("current-datetime",  simple ("${date:now:yyyy-MM-dd HH:mm:ss}") )
        .unmarshal(new JacksonDataFormat(Open511Events.class))
        .marshal().jacksonxml(true)
        .to("xslt:open511.xsl") // transform to HTML as this will be used in the Ghost CMS page update
        .removeHeaders("CamelHttp*") // remove any Camel headers because the HTML is being stored in the Ghost CMS Page       
        .enrich("direct:get-ghots-cms-open511-page", new GhostEventsPageAggregator())         
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken")) //.. likely need to get a new token first but maybe not       
        .setHeader(Exchange.HTTP_METHOD, simple("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.pageid}}" + "?source=html&httpMethod=PUT") // Update Ghost Page with aggregate	-- source=html tells ghost we'll be using html instead of mobiledoc format	
        .end();

    from("direct:get-ghots-cms-open511-page")
        // Generate token and set header for Ghost CMS authorization
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken"))
        // Get page so we can capture date of last change as this will be required for publishing an update.
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.pageid}}" + "?httpMethod=GET")
        .end();

    from("quartz://iotEventsTimer?cron={{drivebc-ghostcms.camel.cron}}") // CRON format for every 10 minutes
        .routeId("iotcentral-ghost-cms")   
         
        // Call IoT Central API
        .setHeader(Exchange.HTTP_METHOD, simple("GET"))
        .setHeader("Authorization", simple("{{iotcentral.auth}}"))   
        .log("API Call to : https://{{iotcentral.weather.api}}")
        .toD("https://{{iotcentral.weather.api}}")

        // Json to XML transformation
        .setHeader("current-datetime",  simple ("${date:now:yyyy-MM-dd HH:mm:ss}") ) //xsl param
        .unmarshal(new JacksonDataFormat(Weather.class))
        .marshal().jacksonxml(true)
        .to("xslt:iotcentral.xsl") 

        // Remove any Camel headers because the HTML is being stored in the Ghost CMS Page
        .removeHeaders("CamelHttp*")        
        
        // Get updated_at from existing page which is required for publishing an update.
        .enrich("direct:get-ghost-update-iotcentral-page", new GhostEventsPageAggregator())
        
        // Call ghostcms PUT API
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken")) 
        .setHeader(Exchange.HTTP_METHOD, simple("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.iotPageid}}" + "?source=html&httpMethod=PUT") // Update Ghost Page with aggregate	-- source=html tells ghost we'll be using html instead of mobiledoc format	
        .end();
    
    from("direct:get-ghost-update-iotcentral-page")
        .setHeader("Authorization", method(GhostApiTokenGeneratorBean.class, "getToken"))
        .toD("http://{{ghostcms.ghostcmshostname}}{{ghostcms.iotPageid}}" + "?httpMethod=GET")
        .end();
  }
}
