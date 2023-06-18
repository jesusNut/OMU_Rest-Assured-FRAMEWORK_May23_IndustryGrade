

This Framework uses Oauth2 authentication.

spotify.oauth2.tests.PlaylistTests.java containes the below Playlist API test cases automated:

1. User should be able to create a Playlist.

2. User should be able to get an already created playlist.

3. User should be able to update an already created playlist.

4. User should NOT be able to create a playlist without name.

5. User should NOT be able to create a playlist with invalid bearer token.

6. User should be able to create a new playlist &&
   add a track to that playlist (fetch json payload from already stored external file) &&
   store response after adding track in external file as JSON for future.

7. Create multiple playlists with data driven names and descriptions from an Excel sheet + Data Provider concept.


