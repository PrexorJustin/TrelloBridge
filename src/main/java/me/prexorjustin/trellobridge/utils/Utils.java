package me.prexorjustin.trellobridge.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utils {

    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    public String injectParameter(String url, String... parameters) {
        if (url == null || url.indexOf('{') == -1) return url;

        Matcher matcher = NAMES_PATTERN.matcher(url);
        StringBuilder stringBuilder = new StringBuilder();
        int paramIndex = 0;

        while (matcher.find()) {
            if (paramIndex >= parameters.length)
                throw new IllegalArgumentException("Not enough parameters provided for URL expansion");

            String replacement = Matcher.quoteReplacement(parameters[paramIndex]);
            matcher.appendReplacement(stringBuilder, replacement);
            paramIndex++;
        }

        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }
}
