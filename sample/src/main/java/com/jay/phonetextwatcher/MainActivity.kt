package com.jay.phonetextwatcher

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jay.phone_text_watcher.AsYouTypeFormatter
import com.jay.phone_text_watcher.PhoneTextWatcher
import com.jay.phone_text_watcher.TextChangeCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSpace = findViewById<EditText>(R.id.editText_space)
        val textViewSpace = findViewById<TextView>(R.id.textView_space)
        // 缺省分隔符为空格
        val phoneTextWatcherSpace = PhoneTextWatcher()
        editTextSpace.addTextChangedListener(phoneTextWatcherSpace)
        // 设置格式化输入的回调
        phoneTextWatcherSpace.setTextChangedCallback(object : TextChangeCallback() {
            override fun afterTextChanged(s: String?, isPhoneNumberValid: Boolean) {
                textViewSpace.text = "反格式化后的手机号为：$s \n是否是有效的手机号：$isPhoneNumberValid"
            }
        })

        val editTextLine = findViewById<EditText>(R.id.editText_line)
        val textViewLine = findViewById<TextView>(R.id.textView_line)
        // 指定分隔符为横线，或者你传入的字符
        val phoneTextWatcherLine = PhoneTextWatcher(AsYouTypeFormatter.SEPARATOR_LINE)
        editTextLine.addTextChangedListener(phoneTextWatcherLine)
        // 设置格式化输入的回调
        phoneTextWatcherLine.setTextChangedCallback(object : TextChangeCallback() {
            override fun afterTextChanged(s: String?, isPhoneNumberValid: Boolean) {
                textViewLine.text = "反格式化后的手机号为：$s \n是否是有效的手机号：$isPhoneNumberValid"
            }
        })

    }
}

