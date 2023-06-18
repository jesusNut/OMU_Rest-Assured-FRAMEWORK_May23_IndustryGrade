package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.extentreports.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class AbstractRestResource {

    static Response post(String path, Object payload, String bearerToken) {

        //fetch request specification

        RequestSpecification requestSpec = CommonSpecBuilder.getRequestSpec();

        //print request on console & do POST call

        Response response = requestSpec.auth().oauth2(bearerToken)
                .body(payload).log().all().post(path);

        //print response on console

        response.then().log().all();

        //log request and response in extent report

        ExtentLogger.logRequest(requestSpec);
        ExtentLogger.logResponse(response);

        return response;
    }


    static Response get(String path, String bearerToken) {

        RequestSpecification requestSpec = CommonSpecBuilder.getRequestSpec();

        Response response = requestSpec
                .auth().oauth2(bearerToken).log().all()
                .get(path);

        response.then().log().all();

        ExtentLogger.logRequest(requestSpec);
        ExtentLogger.logResponse(response);

        return response;
    }

    static Response put(String path, Object payloadToUpdate, String bearerToken) {

        RequestSpecification requestSpec = CommonSpecBuilder.getRequestSpec();

        Response response = requestSpec
                .auth().oauth2(bearerToken)
                .body(payloadToUpdate).log().all()
                .put(path);

        response.then().log().all();

        ExtentLogger.logRequest(requestSpec);
        ExtentLogger.logResponse(response);



        return response;
    }

    static Response postAccount(Map<String, String> formParams, String clientID, String clientSecret) {

        RequestSpecification requestSpec = CommonSpecBuilder.getAccountRequestSpec();

     return requestSpec
                .formParams(formParams)
                .auth().preemptive().basic(clientID, clientSecret)
                .post();


    }

}
