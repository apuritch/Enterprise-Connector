package org.moti.ecp.types;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Weather {
    public List<Result> results = null;
}