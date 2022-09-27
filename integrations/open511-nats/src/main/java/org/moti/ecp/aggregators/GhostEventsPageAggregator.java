package org.moti.ecp.aggregators;

import com.jayway.jsonpath.JsonPath;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.util.json.JsonArray;
import org.apache.camel.util.json.JsonObject;

public class GhostEventsPageAggregator implements AggregationStrategy {
   
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String ghostpage = newExchange.getIn().getBody(String.class);  // the existing ghost page
        String html = oldExchange.getIn().getBody(String.class);  // HTML as to be added to the ghost page

        JsonObject json = new JsonObject();
        JsonObject pages = new JsonObject();
        JsonArray page = new JsonArray();

        String updated = JsonPath.read(ghostpage, "$.pages[0].updated_at"); //query for the updated_at date as this will be used in post to Ghost CMS
       
        json.put("html", html);
        json.put("updated_at",updated);
        page.add(json);
        pages.put("pages",page);
        newExchange.getIn().setBody(pages.toJson());
        return newExchange;
    }
}