
package org.moti.ecp;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "offset"
})
@Generated("jsonschema2pojo")
public class Pagination {

    @JsonProperty("offset")
    public String offset;

}
