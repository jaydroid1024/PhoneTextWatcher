# PhoneTextWatcher
手机号格式化监听器，支持普通输入/删除，中间输入/删除，在任意位置下黏贴/剪贴多个数字等多种交互场景。

目前支持的手机号格式为 `3-4-4`  分隔符可以自定义

## Preview

![video_phone_text_watcher](https://github.com/jaydroid1024/PhoneTextWatcher/blob/master/res/gif_phone_text_watcher.gif?raw=true)





## How to get

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```css
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency

```groovy
dependencies {
    implementation 'com.github.jaydroid1024:PhoneTextWatcher:0.0.2'
}
```



## How to use

```kotlin
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
```



## How to work

//todo



## Todo list

- Change to  SupperTextWatcher to handle `bank card  number` 、 `id card number` 
- Support various formats of mobile phone number formatting input
- Delete the separator and the previous number at the same time 



## Thanks

- [CountryCodePickerProject](https://github.com/hbb20/CountryCodePickerProject)

- [libphonenumber](https://github.com/google/libphonenumber)

