package com.blog.demo.control.text;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

import java.io.File;

public class TextViewFontActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_text_view_font);

        TextView textView = findViewById(R.id.text_view);
        Typeface font = Typeface.createFromAsset(getAssets(),
                "fonts" + File.separator + "digital-7.ttf");
        textView.setTypeface(font);
    }
}
