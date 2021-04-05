# PhoneTextWatcher
手机号格式化监听器，支持普通输入/删除，中间输入/删除，在任意位置下黏贴/剪贴多个数字等多种交互场景。

目前支持的手机号格式为 `3-4-4`  分隔符可以自定义

## Preview

![video_phone_text_watcher](https://github.com/jaydroid1024/PhoneTextWatcher/blob/master/res/video_phone_text_watcher.gif?raw=true)





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
    implementation 'com.github.jaydroid1024:PhoneTextWatcher:0.0.1'
}
```



## How to use

```java
EditText editTextLine = findViewById(R.id.editText_line);
// 指定分隔符为横线，或者你传入的字符
PhoneTextWatcher phoneTextWatcherLine = new PhoneTextWatcher(AsYouTypeFormatter.SEPARATOR_LINE);
editTextLine.addTextChangedListener(phoneTextWatcherLine);

EditText editTextSpace = findViewById(R.id.editText_space);
// 缺省分隔符为空格
PhoneTextWatcher phoneTextWatcherSpace = new PhoneTextWatcher();
editTextSpace.addTextChangedListener(phoneTextWatcherSpace);
```



## How to work

//todo



## Todo list

- Change to  SupperTextWatcher to handle `bank card  number` 、 `id card number` 
- Support various formats of mobile phone number formatting input



## Thanks

[CountryCodePickerProject](https://github.com/hbb20/CountryCodePickerProject)

