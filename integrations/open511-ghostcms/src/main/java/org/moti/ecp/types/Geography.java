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
"type",
"coordinates"
})
@Generated("jsonschema2pojo")
public class Geography {

@JsonProperty("type")
public String type;
@JsonProperty("coordinates")
public List<Object> coordinates = null;  //manually set to type Object after jackson auto creation used type Long

}




