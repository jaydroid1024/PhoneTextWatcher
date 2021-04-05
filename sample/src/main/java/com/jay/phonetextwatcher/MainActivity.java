package com.jay.phonetextwatcher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jay.phone_text_watcher.AsYouTypeFormatter;
import com.jay.phone_text_watcher.PhoneTextWatcher;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextLine = findViewById(R.id.editText_line);
        // 指定分隔符为横线，或者你传入的字符
        PhoneTextWatcher phoneTextWatcherLine = new PhoneTextWatcher(AsYouTypeFormatter.SEPARATOR_LINE);
        editTextLine.addTextChangedListener(phoneTextWatcherLine);

        EditText editTextSpace = findViewById(R.id.editText_space);
        // 缺省分隔符为空格
        PhoneTextWatcher phoneTextWatcherSpace = new PhoneTextWatcher();
        editTextSpace.addTextChangedListener(phoneTextWatcherSpace);


        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            editTextLine.setText("");
            editTextSpace.setText("");
        });

    }
}
