# DriveBC - Open511 Events

This integration is exposed as a REST service for consumption by DriveBC.  It forwards incoming API calls to the Open511 API and returns the results.  While it only acts as a proxy to the Open511 API, it can be modified to faciliate any data transformation and enrichment needs.

## REST

{hostname}:{port}/api - API Root
{hostname}:{port}/api-doc - Api Documentation

## Open API / Swagger UI

{hostname}:{port}/swagger-ui.html

## Diagnostics

<http://{hostname}:{port}/actuator/info>
<http://{hostname}:{port}/actuator/health>
<http://{hostname}:{port}/actuator/camelroutes>
