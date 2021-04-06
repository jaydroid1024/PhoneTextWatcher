package com.jay.phone_text_watcher;

/**
 * 文本变化后的回调
 *
 * @author jaydroid
 * @version 1.0
 * @date 4/6/21
 */
public abstract class TextChangeCallback {

    /**
     * @param unformatted        反格式化后的文本
     * @param isPhoneNumberValid 十一位手机号检验项
     */
    abstract public void afterTextChanged(String unformatted, boolean isPhoneNumberValid);

}
