package com.zakharenko;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Utility class for matching text against regular expressions.
 *
 * <p>Known issues with the original implementation:</p>
 * <ul>
 *   <li><b>Performance Issue:</b> Every invocation of {@code Pattern.compile(regex)}
 *   creates a new {@link Pattern} object. This is inefficient when the same regex is used
 *   repeatedly. To address this, a cache is introduced.</li>
 *   <li><b>Null Input Handling:</b> The original implementation did not validate input
 *   parameters, leading to potential {@link NullPointerException}. The revised implementation
 *   throws an {@link IllegalArgumentException} if {@code regex} or {@code text} is {@code null}.</li>
 *   <li><b>Unused Variable:</b> The intermediate variable {@code result} was unnecessary
 *   and removed for code clarity and brevity.</li>
 * </ul>
 */
public class RegexUtil {

    // Cache for compiled patterns to improve performance
    private static final Map<String, Pattern> patternCache = new HashMap<>();

    /**
     * Checks if the given text matches the specified regular expression.
     *
     * @param regex the regular expression to match against
     * @param text  the text to be checked
     * @return true if the text matches the regex, false otherwise
     * @throws IllegalArgumentException if regex or text is null
     */
    public static boolean matches(String regex, String text) {
        if (regex == null || text == null) {
            throw new IllegalArgumentException("Regex and text must not be null");
        }

        Pattern pattern = patternCache.computeIfAbsent(regex, Pattern::compile);
        return pattern.matcher(text).matches();
    }
}
