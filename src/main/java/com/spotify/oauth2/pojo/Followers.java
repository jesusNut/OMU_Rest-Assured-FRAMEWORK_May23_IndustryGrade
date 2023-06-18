
package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "total"
})
public class Followers {

    @JsonProperty("href")
    private Object href;
    @JsonProperty("total")
    private Integer total;

}
