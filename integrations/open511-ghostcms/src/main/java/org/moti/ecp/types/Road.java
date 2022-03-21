
package org.moti.ecp.types;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "name",
    "from",
    "to",
    "direction"
})
@Generated("jsonschema2pojo")
public class Road {

    @JsonProperty("name")
    public String name;
    @JsonProperty("from")
    public String from;
    @JsonProperty("to")
    public String to;
    @JsonProperty("direction")
    public String direction;

}
