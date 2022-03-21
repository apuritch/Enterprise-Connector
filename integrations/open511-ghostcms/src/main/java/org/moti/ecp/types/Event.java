
package org.moti.ecp.types;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonIgnoreProperties({ "+ivr_message", "+linear_reference_km" })

@JsonPropertyOrder({
    "jurisdiction_url",
    "url",
    "id",
    "headline",
    "status",
    "created",
    "updated",
    "description",
    "+ivr_message",
    "+linear_reference_km",
    "schedule",
    "event_type",
    "event_subtypes",
    "severity",
    "geography",
    "roads",
    "areas"
})
@Generated("jsonschema2pojo")
public class Event {

    @JsonProperty("jurisdiction_url")
    public String jurisdiction_url;
    @JsonProperty("url")
    public String url;
    @JsonProperty("id")
    public String id;
    @JsonProperty("headline")
    public String headline;
    @JsonProperty("status")
    public String status;
    @JsonProperty("created")
    public String created;
    @JsonProperty("updated")
    public String updated;
    @JsonProperty("description")
    public String description;
    // @JsonProperty("+ivr_message")
    // public String _ivr_message;
    // @JsonProperty("+linear_reference_km")
    // public Float _linear_reference_km;
    @JsonProperty("schedule")
    public Schedule schedule;
    @JsonProperty("event_type")
    public String event_type;
    @JsonProperty("event_subtypes")
    public List<String> event_subtypes = null;
    @JsonProperty("severity")
    public String severity;
    @JsonProperty("geography")
    public Geography geography;
    @JsonProperty("roads")
    public List<Road> roads = null;
    @JsonProperty("areas")
    public List<Area> areas = null;

}
