# Integrations

ECP integrations are built in a service-oriented architecture using the Apache Camel open source integration framework. Camel provides a high-degree of flexibility in meeting integration and data distribution needs, is well-supported and widely used.  

For more information on Apache Camel see <https://camel.apache.org/>

## Folder structure

Each integration is implemented in Camel and runs as a stand-alone application. Integrations may contain one or more routes.

* ```./open511events-ghostcms``` Updates GhostCMS based website with recent Open511 API events.
* ```./open511-nats``` NATS messaging system proof of concept. Open511 events are loaded to a NATS queue
* ```./health-checks``` Example project from Camel to ensure build process is working. This can be removed as integrations are established.
* ```./drivebc-open511``` Proxy for Open511 API as used by Drive BC. Enables enriching the Open511 API results.

## Logging

Loging is provided via Logback with MDC enabled and a socket connection to Logstash on port 8002.  This will result in logging to camel* indexes in Elasticsearch.

## Jackson 2.x and JSON Objects

JSON objects from Open511 can be converted to Camel-friendly Java objects using Jackson libraries.  The following site enables creation of Java objects from a source JSON file.  The Jackson annotation style is valuable because it handles non-conforming attribute names from Open511 such as +somevariable.  The + character would otherwise cause marshaling to fail.

https://www.jsonschema2pojo.org/