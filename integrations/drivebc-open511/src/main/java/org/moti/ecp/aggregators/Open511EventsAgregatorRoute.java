package org.moti.ecp.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
public class Open511EventsAgregatorRoute extends RouteBuilder {

  private static final int APP_RESPONSE_CODE = 204;

  @Override
  public void configure() throws Exception {

    from("direct:rest-open511-events")
        .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            // get query string from DriveBC
            String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
            // Set query string for Open511 from DriveBC reqeust
            exchange.getIn().setHeader(Exchange.HTTP_QUERY, queryString);
          }
        })
        .log("API Called")
        .routeId("open511-events-service")
        .onException(HttpOperationFailedException.class)
        .handled(true)    
          .setBody(constant("[]"))
          .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(APP_RESPONSE_CODE)
          .end()

         // bridgeEndpoint=true enables the querystring to be passed through while ignoring the origianl URI    
        .toD("http://{{application.open511.apiroot}}/${header.all}" + "?bridgeEndpoint=true")
        .choice()
        .when(header(Exchange.CONTENT_TYPE).isEqualTo("application/json")) 
           .unmarshal(new JacksonDataFormat())
         ;
  }

}
