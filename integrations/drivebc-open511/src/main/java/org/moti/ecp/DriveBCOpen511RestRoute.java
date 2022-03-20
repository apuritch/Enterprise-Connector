package org.moti.ecp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.camel.model.rest.RestBindingMode;

/**
 * Rest API to be consumed by DriveBC - Proxy to Open511
 */

@Component
public class DriveBCOpen511RestRoute extends RouteBuilder {

  @Value("${camel.servlet.mapping.context-path}")
  private String contextPath;

  @Override
  public void configure() {

    // Configure the REST service
    restConfiguration()
        .component("servlet")
        .contextPath(contextPath.substring(0, contextPath.length() - 2))
        .enableCORS(true)
        .bindingMode(RestBindingMode.json)
        // turn on Swagger api-doc
        .apiContextPath("{{api.doc.path}}")
        .apiProperty("api.title", "DriveBC Open511 Proxy API")
        .apiProperty("api.version", "1.0.0");
      

    rest().description("DriveBC Open511 REST service")

        .get("{all}").description("Proxy to Open511 API - all routes and parameters are accepted")
        .route().routeId("rest-open511-events")
        .to("direct:rest-open511-events")
        .endRest()

    // --- We could proxy specific endpoints instead of simply passing everything to
    // Open511
    // .get("/events")
    // .route().routeId("rest-open511-events")
    // .to("direct:rest-open511-events")
    // .endRest()
    ; // end rest()
  }
}
