package com.spotify.oauth2.extentreports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spotify.oauth2.constants.FrameworkConstants;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

// log pass

    public static void pass(String details) {

        ExtentManager.getExtentTest().pass(details);

    }

    // log fail

    public static void fail(String details) {

        ExtentManager.getExtentTest().fail(details);

    }

    // log skip

    public static void skip(String details) {

        ExtentManager.getExtentTest().skip(MarkupHelper.createLabel(details, ExtentColor.AMBER));

    }

    // log Info

    public static void info(String details) {

        ExtentManager.getExtentTest().info(details);

    }

    // log Info:markup

    public static void info(Markup markup) {

        ExtentManager.getExtentTest().info(markup);

    }

    // log exception in formatted way

    public static void logStackTraceInfoInExtentReport(String message) {


        String formattedText = "<pre>" + message.replace(",", "<br>") + "</pre>";
        ExtentManager.getExtentTest().fail(MarkupHelper.createLabel("EXCEPTION : ", ExtentColor.RED));
        ExtentManager.getExtentTest().fail(formattedText);
    }

    // to log request including request body

    public static void logRequest(RequestSpecification requestSpecification) {

        logPrettyRequestToReport(requestSpecification);

    }


    // to log response

    public static void logResponse(Response response) {

        logPrettyResponseToReport(response);

    }

    // to log response - private method

    private static void logPrettyResponseToReport(Response response) {

        info(MarkupHelper.createLabel("$$$$$ LOGGING RESPONSE $$$$$$ ", ExtentColor.PURPLE));

        info(MarkupHelper.createLabel("$$$$$ LOGGING RESPONSE STATUS CODE & STATUS LINE : ", ExtentColor.PURPLE));

        info(response.getStatusCode() + " - " + response.getStatusLine());

        info(MarkupHelper.createLabel("$$$$$ LOGGING RESPONSE BODY : ", ExtentColor.PURPLE));

        info(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));

        info(MarkupHelper.createLabel("$$$$$ LOGGING RESPONSE HEADERS : ", ExtentColor.PURPLE));

        looper(response.getHeaders());

        info(MarkupHelper.createLabel("$$$$$ LOGGING RESPONSE COOKIES : ", ExtentColor.PURPLE));

        mapper(response.getCookies());


    }

    // to log request inluding request body - private method

    private static void logPrettyRequestToReport(RequestSpecification requestSpecification) {

        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);


        info(MarkupHelper.createLabel("##### LOGGING REQUEST DETAILS ##### : ", ExtentColor.GREY));

        info(MarkupHelper.createLabel("##### LOGGING REQUEST URI : ", ExtentColor.GREY));
        info(FrameworkConstants.BOLD_START + query.getURI() + FrameworkConstants.BOLD_END);

		//if the HTTP method in the request being logged is GET/DELETE, then skip logging the request body as GET and DELETE do not have body.

        if (!(query.getMethod().equalsIgnoreCase("get") || query.getMethod().equalsIgnoreCase("delete"))) {

            info(MarkupHelper.createLabel("##### LOGGING REQUEST BODY : ", ExtentColor.GREY));
            info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));

        }

        info(MarkupHelper.createLabel("##### LOGGING HEADERS : ", ExtentColor.GREY));
        looper(query.getHeaders());

        info(MarkupHelper.createLabel("##### LOGGING COOKIES : ", ExtentColor.GREY));
        looper(query.getCookies());

        info(MarkupHelper.createLabel("##### LOGGING REQUEST PARAMS : ", ExtentColor.GREY));
        mapper(query.getRequestParams());

        info(MarkupHelper.createLabel("##### LOGGING QUERY PARAMS : ", ExtentColor.GREY));
        mapper(query.getQueryParams());

        info(MarkupHelper.createLabel("##### LOGGING PATH PARAMS : ", ExtentColor.GREY));
        mapper(query.getPathParams());

        info(MarkupHelper.createLabel("##### LOGGING FORM PARAMS : ", ExtentColor.GREY));
        mapper(query.getFormParams());

    }


    // helper method - 1

    private static void looper(Object object) {

        if (object instanceof Headers) {

            Headers headers = (Headers) object;

            for (Header header : headers) {

                info(header.getName() + " : " + header.getValue());
            }

        }

        if (object instanceof Cookies) {

            Cookies cookies = (Cookies) object;

            for (Cookie cookie : cookies) {

                info(cookie.getName() + " : " + cookie.getValue());
            }

        }

    }

    // helper method- 2

    private static void mapper(Map<String, String> map) {

        Set<Entry<String, String>> entrySet = map.entrySet();

        for (Entry<String, String> entry : entrySet) {

            info(entry.getKey() + " : " + entry.getValue());
        }

    }


}
