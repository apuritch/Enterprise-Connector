package org.moti.ecp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import org.apache.camel.model.rest.RestBindingMode;

/**
 * Rest API to be consumed by DriveBC - Proxy to Open511
 */

@Component
public class DriveBCOpen511RestRoute extends RouteBuilder {

  @Override
  public void configure() {

    // Configure the REST service
    restConfiguration()
        .component("netty-http")
        .port("{{application.port}}")
        .enableCORS(true)
        .bindingMode(RestBindingMode.json)
        // turn on swagger api-doc
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "DriveBC Open511 Proxy API")
        .apiProperty("api.version", "1.0.0");

    // rest("/api")
    rest()
        .path("/api") // This makes the API available at http://host:port/$CONTEXT_ROOT/api
        .get("{all}")
        .route().routeId("rest-open511-events")
        .to("direct:rest-open511-events")
        .endRest()

        // --- We could proxy specific endpoints instead of simply passing everything to Open511
        // .get("/events")
        // .route().routeId("rest-open511-events")
        // .to("direct:rest-open511-events")
        // .endRest()
    ; // end rest()
  }
}
