package org.moti.ecp.types;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"intervals"
})
@Generated("jsonschema2pojo")
public class Schedule {

@JsonProperty("intervals")
public List<String> intervals = null;
@JsonProperty("recurring_schedules")
public List<Object> recurring_schedules = null;
}