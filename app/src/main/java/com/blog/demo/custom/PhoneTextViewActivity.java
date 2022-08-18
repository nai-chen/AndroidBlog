package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blog.demo.custom.widget.PhoneTextView;

public class PhoneTextViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PhoneTextView(this));
    }

}
