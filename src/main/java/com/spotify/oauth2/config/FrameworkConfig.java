package com.spotify.oauth2.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "system:env", "file:${user.dir}/src/test/resources/config/FrameworkConfig.properties"})
 interface FrameworkConfig extends Config {

 @Key("grant_type")
String grantType();

 @Key("refresh_token")
 String refreshToken();

 @Key("client_id")
 String clientId();

 @Key("client_secret")
 String clientSecret();

 @Key("spotifyUserID")
 String userID();

}
