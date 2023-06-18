package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(setterPrefix = "set")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddedTrackResponse {

    @JsonProperty("snapshot_id")
    private String snapshot_id;
}
