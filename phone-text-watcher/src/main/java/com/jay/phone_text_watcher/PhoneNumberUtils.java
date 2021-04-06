package com.jay.phone_text_watcher;

/**
 * 手机号格式化工具类
 *
 * @author jaydroid
 * @version 1.0
 * @date 4/5/21
 */
class PhoneNumberUtils {

    public static PhoneNumberUtils createInstance() {
        return new PhoneNumberUtils();
    }

    /**
     * True if c is ISO-LATIN characters 0-9, *, # , +
     */
    public static boolean isNonSeparator(char c) {
        return (c >= '0' && c <= '9') || c == '*' || c == '#' || c == '+';
    }

    public static AsYouTypeFormatter getAsYouTypeFormatter() {
        return new AsYouTypeFormatter();
    }

}
