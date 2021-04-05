package com.jay.phone_text_watcher;

/**
 * 输入时格式化类，用于在输入电话号码时对其进行格式化。
 * 可以通过调用{@link PhoneNumberUtils#getAsYouTypeFormatter}来创建AsYouTypeFormatter。
 * 可以通过调用{@link #inputDigit}来添加数字，并且每次添加数字时都将返回部分格式化后的电话号码。
 * 可以在格式化新号码之前调用{@link #clear}。
 *
 * @author jaydroid
 * @version 1.0
 * @date 4/5/21
 */
public class AsYouTypeFormatter {

    //横线分隔符
    public static char SEPARATOR_LINE = '-';
    //空格分隔符
    public static char SEPARATOR_SPACE = ' ';

    private final StringBuilder accruedInput = new StringBuilder();
    private char separator = SEPARATOR_SPACE;

    /**
     * 设置格式化分隔符，默认为空格
     *
     * @param separator 分隔符
     */
    public void setSeparator(char separator) {
        this.separator = separator;
    }

    /**
     * 累计输入数字
     *
     * @param nextChar 电话号码中最近输入的数字。
     * @return
     */
    public String inputDigit(char nextChar) {
        accruedInput.append(nextChar);
        String accruedInputNumber = accruedInput.toString();
        String formattedNumber = attemptToFormatAccruedDigits(accruedInputNumber);
        if (formattedNumber.length() > 0) {
            return formattedNumber;
        }
        return accruedInputNumber;
    }


    /**
     * 尝试格式化累积的数字
     *
     * @param accruedInputNumber 累计输入的电话号码
     * @return 格式化后的电话号码
     */
    private String attemptToFormatAccruedDigits(String accruedInputNumber) {
        String formattedNumber;
        StringBuilder formattingNumber = new StringBuilder();
        if (!accruedInputNumber.isEmpty()) {
            //插入分隔符
            for (int i = 0; i < accruedInputNumber.length(); i++) {
                // 手机号的格式化为344(xxx xxxx xxxx),所以只在第 4，9 的位置插入分隔符
                if (i == 3 || i == 8 || accruedInputNumber.charAt(i) != separator) {
                    formattingNumber.append(accruedInputNumber.charAt(i));
                    //是否是包含空格的长度
                    boolean isSpaceIndex = formattingNumber.length() == 4 || formattingNumber.length() == 9;
                    //最后一个字符是否为空格，
                    boolean isLastSpace = formattingNumber.charAt(formattingNumber.length() - 1) != separator;
                    //最后一个字符不为空格
                    if (isSpaceIndex && isLastSpace) {
                        //在最后位置插入空格
                        formattingNumber.insert(formattingNumber.length() - 1, separator);
                    }
                }
            }
        }
        formattedNumber = formattingNumber.toString();
        return formattedNumber;
    }

    /**
     * 清空累计的数字
     */
    public void clear() {
        accruedInput.setLength(0);
    }

}
