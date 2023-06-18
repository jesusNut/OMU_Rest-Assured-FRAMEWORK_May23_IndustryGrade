package com.spotify.oauth2.utils;


import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.exception.FrameworkException;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileReadWriteUtils {


    public static String readJSONFileAndGetAsString(String filenameWithExtension) {

        String jsonContent = null;

        try {

            jsonContent = new String(
                    Files.readAllBytes(Paths.get(FrameworkConstants.REQUEST_JSON_FOLDER_PATH + filenameWithExtension)));

        } catch (IOException e) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE
                    + " => Issue while Reading JSON File for creating API request", e);

        }

        return jsonContent;

    }

    public static void storeResponseAsJSONFile(String filenameWithExtension, Response response) {

        try {


            Path path = Paths.get(FrameworkConstants.RESPONSE_JSON_FOLDER_PATH + filenameWithExtension);

            Files.write(path,
                    response.asByteArray());

        } catch (IOException e) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE
                    + " => Issue while writing API response as JSON File", e);

        }

    }


}
