package com.spotify.oauth2.config;


import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.exception.FrameworkException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConfigFactory {

    private static FrameworkConfig getConfig() {

        return org.aeonbits.owner.ConfigFactory.create(FrameworkConfig.class);

    }


    public static String getClientID() {

        String clientId =  getConfig().clientId();

        if (!((Objects.isNull(clientId)) || clientId.isBlank())) {

            return clientId.trim();
        }

        else {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> 'client_id' is either null or blank. \n Investigate in System properties/System environment/config.properties files");
        }

    }

    public static String getClientSecret() {


        String clientSecret = getConfig().clientSecret();

        if (!((Objects.isNull(clientSecret)) || clientSecret.isBlank())) {

            return clientSecret.trim();
        }

        else {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> 'client_secret' is either null or blank. \n Investigate in System properties/System environment/config.properties files");
        }

    }

    public static String getGrantType() {

        String grantType = getConfig().grantType();

        if (!((Objects.isNull(grantType)) || grantType.isBlank())) {

            return grantType.trim();
        }

        else {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> 'grant_type' is either null or blank. \n Investigate in System properties/System environment/config.properties files");
        }

    }


    public static String getRefreshToken() {

        String refreshToken =  getConfig().refreshToken();

        if (!((Objects.isNull(refreshToken)) || refreshToken.isBlank())) {

            return refreshToken.trim();
        }

        else {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> 'refresh_token' is either null or blank. \n Investigate in System properties/System environment/config.properties files");
        }

    }

    public static String getSpotifyUserID() {

        String userId =  getConfig().userID();

        if (!((Objects.isNull(userId)) || userId.isBlank())) {

            return userId.trim();
        }

        else {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> 'spotifyUserID' is either null or blank. \n Investigate in System properties/System environment/config.properties files");
        }
    }


}
