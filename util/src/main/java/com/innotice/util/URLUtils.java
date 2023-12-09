package com.innotice.util;

import java.net.URI;

public class URLUtils {

    public static boolean isUrl(String url) {
        try {
            URI.create(url).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
