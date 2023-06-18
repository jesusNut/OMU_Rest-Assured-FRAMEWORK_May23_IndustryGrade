package com.spotify.oauth2.api.applicationApi;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonSpecBuilder {


    static RequestSpecification getRequestSpec() {

        return given().baseUri("https://api.spotify.com")
                .basePath(Endpoints.BASE_PATH)
                .contentType(ContentType.JSON);
    }

    public static ResponseSpecification getResponseSpec() {

        return expect()
                .contentType(ContentType.JSON);

    }


    static RequestSpecification getAccountRequestSpec() {

        return given()
                .baseUri("https://accounts.spotify.com")
                .basePath(Endpoints.API + Endpoints.TOKEN)
                .contentType(ContentType.URLENC);

    }

}
