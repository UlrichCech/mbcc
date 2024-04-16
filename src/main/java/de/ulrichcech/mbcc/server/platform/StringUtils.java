package de.ulrichcech.mbcc.server.platform;

/**
 *  Helper class for checking strings. Replacement of the Apache commons-lang library.
 *
 *  @author Ulrich Cech
 */
public class StringUtils {


    private StringUtils() {
    }


    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (var i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
