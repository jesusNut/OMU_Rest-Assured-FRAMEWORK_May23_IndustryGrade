package spotify.oauth2.tests;


import com.spotify.oauth2.annotations.FrameworkAnnotation;
import com.spotify.oauth2.api.applicationApi.CommonSpecBuilder;
import com.spotify.oauth2.api.applicationApi.PlaylistAPIBuilder;
import com.spotify.oauth2.enums.Category;
import com.spotify.oauth2.errorpojo.ErrorOuter;
import com.spotify.oauth2.pojo.AddedTrackResponse;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.FileReadWriteUtils;
import com.spotify.oauth2.utils.RandomDataUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;


public class PlaylistTests {

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldBeAbleToCreatePlaylist() {

        Playlist payload = Playlist.builder().setName(RandomDataUtils.getRandomPlaylistName())
                .setDescription(RandomDataUtils.getRandomPlaylistDesc())
                .set_public(false)
                .build();

        Response response = PlaylistAPIBuilder.post(payload);

        Playlist responseAsPlaylist = response.as(Playlist.class);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
        Assertions.assertThat(responseAsPlaylist.getDescription()).isEqualTo(payload.getDescription());
        Assertions.assertThat(responseAsPlaylist.getName()).isEqualTo(payload.getName());
        Assertions.assertThat(responseAsPlaylist.get_public()).isEqualTo(payload.get_public());

    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldBeAbleToGetAPlaylist() {

        Response response = PlaylistAPIBuilder.get("08wwmfqt8s1wnpqNeESn7d");

        Playlist responseAsPlaylist = response.as(Playlist.class);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(responseAsPlaylist.getDescription()).isEqualTo("DESCRIPTION: DONOT DELETE");
        Assertions.assertThat(responseAsPlaylist.getName()).isEqualTo("DONOT DELETE");
        Assertions.assertThat(responseAsPlaylist.get_public()).isEqualTo(true);
    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldBeAbleToUpdatePlaylistDetails() {

        var variableStringPart = RandomDataUtils.getRandomNumber();

        Playlist payloadToUpdate = Playlist.builder().setName("Changer_" + variableStringPart)
                .setDescription("Description of changer_" + variableStringPart)
                .set_public(false)
                .build();

        Response response = PlaylistAPIBuilder.put(payloadToUpdate, "5h6fIftRAnXRysYMBDfzc1");

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {

        Playlist payload = Playlist.builder().setName("")
                .setDescription(RandomDataUtils.getRandomPlaylistDesc())
                .set_public(false)
                .build();


        Response response = PlaylistAPIBuilder.post(payload);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        ErrorOuter responseAsErrorOuter = response.as(ErrorOuter.class);

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(400);
        Assertions.assertThat(responseAsErrorOuter.getError().getMessage()).isEqualTo("Missing required field: name");
        Assertions.assertThat(responseAsErrorOuter.getError().getStatus()).isEqualTo(400);

    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {


        Playlist payload = Playlist.builder().setName(RandomDataUtils.getRandomPlaylistName())
                .setDescription(RandomDataUtils.getRandomPlaylistDesc())
                .set_public(false)
                .build();

        Response response = PlaylistAPIBuilder.post(payload, "1234");

        ErrorOuter responseAsErrorOuter = response.as(ErrorOuter.class);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(401);
        Assertions.assertThat(responseAsErrorOuter.getError().getMessage()).isEqualTo("Invalid access token");
        Assertions.assertThat(responseAsErrorOuter.getError().getStatus()).isEqualTo(401);

    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test
    public void shouldBeAbletoCreatePlaylist_addTrack() {

        //code to create a new playlist

        Playlist payload = Playlist.builder().setName(RandomDataUtils.getRandomPlaylistName())
                .setDescription(RandomDataUtils.getRandomPlaylistDesc())
                .set_public(false)
                .build();

        Response response = PlaylistAPIBuilder.post(payload);

        Playlist responseAsPlaylist = response.as(Playlist.class);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);

        //Fetch playlistID from the response

        String playlistID = responseAsPlaylist.getId();

        //code to add a track to the playlist

        String payloadForAddingTrack = FileReadWriteUtils.readJSONFileAndGetAsString("addATrackToPlaylist.json");

        Response response1 = PlaylistAPIBuilder.post(playlistID, payloadForAddingTrack);


        AddedTrackResponse responseAfterAddingTrack = response1.as(AddedTrackResponse.class);

        // validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse1 =
                response1.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response1.getStatusCode()).isEqualTo(201);
        Assertions.assertThat(responseAfterAddingTrack.getSnapshot_id()).isAlphanumeric();

        //store response as JSON to an external file

        FileReadWriteUtils.storeResponseAsJSONFile("SnapshotAfterAddingTrack.json", response1);


    }

    @FrameworkAnnotation(category = { Category.REGRESSION })
    @Test( dataProvider = "getData", dataProviderClass = com.spotify.oauth2.utils.DataProviderUtils.class)
    public void shouldBeAbleToCreateMutliplePlaylistsExcelDP(Map<String, String> data) {

        Playlist payload = Playlist.builder().setName(data.get("name"))
                .setDescription(data.get("description"))
                .set_public(false)
                .build();

        Response response = PlaylistAPIBuilder.post(payload);

        Playlist responseAsPlaylist = response.as(Playlist.class);

        //validating common response attributes among tests by storing and using in 'responseSpecification'

        ValidatableResponse validatableResponse =
                response.then().assertThat().spec(CommonSpecBuilder.getResponseSpec());

        //validating response attributes specific to this test.

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
        Assertions.assertThat(responseAsPlaylist.getDescription()).isEqualTo(payload.getDescription());
        Assertions.assertThat(responseAsPlaylist.getName()).isEqualTo(payload.getName());
        Assertions.assertThat(responseAsPlaylist.get_public()).isEqualTo(payload.get_public());

    }

}
