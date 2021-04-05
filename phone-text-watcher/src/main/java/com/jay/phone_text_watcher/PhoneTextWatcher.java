package com.jay.phone_text_watcher;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;


/**
 * 手机号格式化监听
 * 支持多种交互场景的:包括普通输入/删除，中间输入/删除，在任意位置下黏贴/删除多个字符串。
 *
 * @author jaydroid
 * @version 1.0
 * @date 4/5/21
 */
public class PhoneTextWatcher implements TextWatcher {

    private static final String TAG = "Phone_TextWatcher";

    //格式化器，用于在输入电话号码时对其进行格式化
    private final AsYouTypeFormatter mFormatter;
    //表示更改是由我们自己造成的。
    private boolean mSelfChange = false;
    //表示格式化已停止。
    private boolean mStopFormatting;

    public PhoneTextWatcher() {
        this(AsYouTypeFormatter.SEPARATOR_SPACE);
    }

    public PhoneTextWatcher(char separator) {
        mFormatter = PhoneNumberUtils.getAsYouTypeFormatter();
        mFormatter.setSeparator(separator);
        mFormatter.clear();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mSelfChange || mStopFormatting) {
            return;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mSelfChange || mStopFormatting) {
            return;
        }
    }

    @Override
    public synchronized void afterTextChanged(Editable s) {
        if (mStopFormatting) {
            // 清除所有文本后，重新开始格式化。
            mStopFormatting = !(s.length() == 0);
            return;
        }
        if (mSelfChange) {
            // 忽略由格式化字符替换原字符引起的更改。
            return;
        }
        //获取光标的结束点
        int selectionEnd = Selection.getSelectionEnd(s);
        //光标是否在最后
        boolean isCursorAtEnd = (selectionEnd == s.length());
        //获取此号码的格式化文本
        String formatted = format(s);
        //现在计算格式化后文本中的光标位置
        int finalCursorPosition = 0;
        if (formatted.equals(s.toString())) {
            //表示在格式化时没有变化，不会移动光标
            finalCursorPosition = selectionEnd;
        } else if (isCursorAtEnd) {
            //如果光标已经在末尾，则将其放在末尾。
            finalCursorPosition = formatted.length();
        } else {
            //确定格式化前的光标位置
            int digitsBeforeCursor = 0;
            for (int i = 0; i < s.length(); i++) {
                if (i >= selectionEnd) {
                    break;
                }
                //不包含分隔符的数字
                if (PhoneNumberUtils.isNonSeparator(s.charAt(i))) {
                    digitsBeforeCursor++;
                }
            }
            //在格式化文本中找到该位置
            for (int i = 0, digitPassed = 0; i < formatted.length(); i++) {
                if (digitPassed == digitsBeforeCursor) {
                    finalCursorPosition = i;
                    break;
                }
                if (PhoneNumberUtils.isNonSeparator(formatted.charAt(i))) {
                    digitPassed++;
                }
            }
        }
        //如果此操作在分隔符之前结束，将其进一步移动，以免误删除分隔符
        if (!isCursorAtEnd) {
            while (0 < finalCursorPosition - 1 && !PhoneNumberUtils.isNonSeparator(formatted.charAt(finalCursorPosition - 1))) {
                finalCursorPosition--;
            }
        }
        //开始将原内容替换为格式化的内容
        try {
            if (formatted != null) {
                mSelfChange = true;
                s.replace(0, s.length(), formatted, 0, formatted.length());
                mSelfChange = false;
                Selection.setSelection(s, finalCursorPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 格式化。
     */
    private String format(CharSequence s) {
        String internationalFormatted = "";
        mFormatter.clear();
        char lastNonSeparator = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //不包含分隔符
            if (PhoneNumberUtils.isNonSeparator(c)) {
                if (lastNonSeparator != 0) {
                    //输入每个数字后，格式化电话号码。
                    internationalFormatted = mFormatter.inputDigit(lastNonSeparator);
                }
                lastNonSeparator = c;
            }
        }
        //最后一位
        if (lastNonSeparator != 0) {
            internationalFormatted = mFormatter.inputDigit(lastNonSeparator);
        }
        internationalFormatted = internationalFormatted.trim();
        return TextUtils.isEmpty(internationalFormatted) ? "" : internationalFormatted;
    }

    /**
     * 停止格式化
     */
    private void stopFormatting() {
        mStopFormatting = true;
        mFormatter.clear();
    }

    /**
     * 判断是否分隔符
     */
    private boolean hasSeparator(final CharSequence s, final int start, final int count) {
        for (int i = start; i < start + count; i++) {
            char c = s.charAt(i);
            // 不是有效的号码数字说明是分隔符
            if (!PhoneNumberUtils.isNonSeparator(c)) {
                return true;
            }
        }
        return false;
    }
}
