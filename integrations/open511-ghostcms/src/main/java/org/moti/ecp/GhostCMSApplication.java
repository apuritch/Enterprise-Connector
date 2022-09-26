package org.moti.ecp;

import org.apache.camel.zipkin.starter.CamelZipkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.apache.camel.opentracing.starter.CamelOpenTracing;

@SpringBootApplication
//@CamelOpenTracing
@CamelZipkin
public class GhostCMSApplication {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(GhostCMSApplication.class, args);
    }

}
