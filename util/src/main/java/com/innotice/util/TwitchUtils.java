package com.innotice.util;

public class TwitchUtils {

    private static final String URL = "https://www.twitch.tv/";

    public static String generateChannelUrl(String channelName) {
        return URL + channelName;
    }

}
