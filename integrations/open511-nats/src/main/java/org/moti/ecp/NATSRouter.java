package org.moti.ecp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.nats.NatsComponent;
import org.springframework.stereotype.Component;

/**
 * Camel route to load most recent Open 511 to a NATS Queue
 * site.
 */
@Component
public class NATSRouter extends RouteBuilder {

  @Override
  public void configure() {


    // from("quartz://open511eventsTimer?cron={{open511-nats.camel.cron}}") // CRON format for
    //     .routeId("open511-nats")

    //     .toD(
    //         "http://{{application.open511.apiroot}}/events?{{application.open511.parameters}}&updated=>${date-with-timezone:now-0h{{open511-nats.polling.interval.minutes}}m:PST:yyyy-MM-dd HH:mm}&bridgeEndpoint=true")
    //         .split().jsonpathWriteAsString("$.events.[*]")
    //         .to("direct:loggy")
    //     .choice()
    //       .when().jsonpath("$.events[?(@.length() < 1)]")
    //          .log ("***** No New Events ******")
    //       .otherwise()
    //          .log( "#######  Events found and sent to NATS ####### ")
    //          .to("nats:test?servers={{nats.server}}")
    //      .end();

    //     // loop to show whats in the NATS 
    //     // from("nats:test?servers={{nats.server}}&maxMessages=3")     
    //     // .to("stream:out")   ;
    //     from ("direct:loggy")
    //     .to("log:hello");

      }

     


}
