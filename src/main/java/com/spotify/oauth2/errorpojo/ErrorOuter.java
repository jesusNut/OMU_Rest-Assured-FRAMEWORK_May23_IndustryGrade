package com.spotify.oauth2.errorpojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
public class ErrorOuter {

    @JsonProperty("error")
    private ErrorInner error;

}
