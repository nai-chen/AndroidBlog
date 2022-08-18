package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blog.demo.custom.widget.GestureView;

public class GestureViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new GestureView(this));
    }
}
