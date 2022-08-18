package com.blog.demo.control.text;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TextViewLineSpacingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_text_view_line_spacing);

        TextView textView = findViewById(R.id.text_view);
        textView.setLineSpacing(20, 1.5f);
    }

}
