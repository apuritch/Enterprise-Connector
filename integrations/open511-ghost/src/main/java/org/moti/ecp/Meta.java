
package org.moti.ecp;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "up_url",
    "version"
})
@Generated("jsonschema2pojo")
public class Meta {

    @JsonProperty("url")
    public String url;
    @JsonProperty("up_url")
    public String up_url;
    @JsonProperty("version")
    public String version;

}
