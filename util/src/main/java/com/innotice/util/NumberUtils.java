package com.innotice.util;

import java.util.regex.Pattern;

public class NumberUtils {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^(0|[1-9][0-9]*)$");

    public static boolean isNumeric(String string) {
        return string != null && NUMERIC_PATTERN.matcher(string).find();
    }

}
