package com.spotify.oauth2.api.applicationApi;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Endpoints {

    //app level
    public static final String BASE_PATH = "/v1";

    //Playlist API
    public static final String USERS = "/users";
    public static final String PLAYLISTS = "/playlists";
    public static final String TRACKS = "/tracks";

    //Accounts API
    public static final String API = "/api";
    public static final String TOKEN = "/token";

}
