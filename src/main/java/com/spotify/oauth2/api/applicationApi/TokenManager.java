package com.spotify.oauth2.api.applicationApi;


import com.spotify.oauth2.config.FrameworkConfigFactory;
import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.exception.FrameworkException;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class TokenManager {

    private static String accessToken;
    private static Instant expiry_time;

    public static synchronized String getBearerToken() {


        if (Objects.isNull(accessToken) || Instant.now().isAfter(expiry_time)) {

            Response response = renewTokenUsingRefreshToken();
            accessToken = response.jsonPath().getString("access_token");

            int expiryDurationInSeconds = response.jsonPath().getInt("expires_in");
            expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);


        }

        return accessToken;

    }


    private static Response renewTokenUsingRefreshToken() {

        Map<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", FrameworkConfigFactory.getGrantType());
        formParams.put("refresh_token", FrameworkConfigFactory.getRefreshToken());

        Response response = AbstractRestResource.postAccount(formParams, FrameworkConfigFactory.getClientID(), FrameworkConfigFactory.getClientSecret());

        if (!(response.getStatusCode() == 200)) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + " ==> Bearer Token RENEWAL Using Refresh Token FAILED !!!");

        }

        return response;

    }

}
