package com.spotify.oauth2.api.applicationApi;


import com.spotify.oauth2.config.FrameworkConfigFactory;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlaylistAPIBuilder {


    public static Response post(Playlist payload) {

        return AbstractRestResource.post(Endpoints.USERS+"/"+ FrameworkConfigFactory.getSpotifyUserID() +Endpoints.PLAYLISTS,payload, TokenManager.getBearerToken());

    }

    public static Response post(String playlistID, String payload) {

        return AbstractRestResource.post(Endpoints.PLAYLISTS+"/"+playlistID+Endpoints.TRACKS,payload, TokenManager.getBearerToken());

    }

    public static Response post(Playlist payload, String bearerToken) {

        return  AbstractRestResource.post(Endpoints.USERS+"/"+FrameworkConfigFactory.getSpotifyUserID()+Endpoints.PLAYLISTS,payload,bearerToken);

    }

    public static Response get(String playlistID) {

        return AbstractRestResource.get(Endpoints.PLAYLISTS+"/"+playlistID, TokenManager.getBearerToken());

    }

    public static Response put(Playlist payloadToUpdate, String playlistID) {

        return AbstractRestResource.put(Endpoints.PLAYLISTS+"/"+playlistID, payloadToUpdate, TokenManager.getBearerToken());

    }


}
