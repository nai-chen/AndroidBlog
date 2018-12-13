package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/3.
 */

public class SpanRangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_span_range);

        EditText et = (EditText) findViewById(R.id.et);
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(new ForegroundColorSpan(Color.RED), 3, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        et.setText(ss);
    }
}
