package com.spotify.oauth2.utils;


import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class FakerUtils {

// implementing Faker Utils via singleton + Facade (RandomUtils)

    private static Faker FAKER = null;

    static synchronized Faker getFakerInstance() {

        if (FAKER == null) {

            FAKER = new Faker();
        }

        return FAKER;

    }

    static String getRandomName(int length) {

        return getFakerInstance().regexify("^[a-zA-Z0-9]{" + length + "}$");
    }

    static String getRandomDescription(int length) {

        return getFakerInstance().regexify("[A-Za-z0-9_@./#&+-]{" + length + "}");
    }

    static int getRandomNumber(int startValue, int endValue) {

        return getFakerInstance().number().numberBetween(startValue, endValue);
    }


}
