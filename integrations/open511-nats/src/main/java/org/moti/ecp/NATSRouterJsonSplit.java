package org.moti.ecp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * Camel route to load most recent Open 511 to a NATS Queue
 * site.
 */

@Component
public class NATSRouterJsonSplit extends RouteBuilder {
  @Override
  public void configure() {

    //from("quartz://open511eventsTimer?cron={{open511-nats.camel.cron}}") // CRON format for
    from("timer://Open511Query?fixedRate=true&period=600000")
    //.log ("http://{{application.open511.apiroot}}/events?{{application.open511.parameters}}&updated=>${date-with-timezone:now-0h{{open511-nats.polling.interval.minutes}}m:PST:yyyy-MM-dd HH:mm}&bridgeEndpoint=true")
    .log ("http://{{application.open511.apiroot}}/events?{{application.open511.parameters}}&updated=>${date-with-timezone:now-0h{{open511-nats.polling.interval.minutes}}m:-0800:yyyy-MM-dd'T'HH:mm:ss'Z'}&bridgeEndpoint=true")
    .streamCaching()
        .routeId("open511-nats-events")
        .toD(
            "http://{{application.open511.apiroot}}/events?{{application.open511.parameters}}&updated=>${date-with-timezone:now-0h{{open511-nats.polling.interval.minutes}}m:-0800:yyyy-MM-dd'T'HH:mm:ss'Z'}&bridgeEndpoint=true")
          
            .choice()
        .when(header(Exchange.CONTENT_TYPE).isEqualTo("application/json"))
          .unmarshal(new JacksonDataFormat())
          .split().jsonpathWriteAsString("$.events[*]")
          .streaming()
          .log("${body}")
          .to("nats:test?servers={{nats.server}}");
  }
}
