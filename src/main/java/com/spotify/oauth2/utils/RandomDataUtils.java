package com.spotify.oauth2.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomDataUtils {

    public static String getRandomPlaylistName() {

        return "Playlist_" + FakerUtils.getRandomName(7);
    }

    public static String getRandomPlaylistDesc() {

        return "Description : " + FakerUtils.getRandomDescription(40);
    }

    public static int getRandomNumber() {

        return FakerUtils.getRandomNumber(1, 300);
    }

}
