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
        EditText editTextSpace = findViewById(R.id.editText_space);
        Button button = findViewById(R.id.button);

        PhoneTextWatcher phoneTextWatcherLine = new PhoneTextWatcher(AsYouTypeFormatter.SEPARATOR_LINE);
        editTextLine.addTextChangedListener(phoneTextWatcherLine);

        PhoneTextWatcher phoneTextWatcherSpace = new PhoneTextWatcher(AsYouTypeFormatter.SEPARATOR_SPACE);
        editTextSpace.addTextChangedListener(phoneTextWatcherSpace);

        button.setOnClickListener(v -> {
            editTextLine.setText("");
            editTextSpace.setText("");
        });

    }
}
