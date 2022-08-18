package com.blog.demo.component.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class IntentCategoryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_intent_category);

        TextView tvIntent = findViewById(R.id.tv_intent);
        tvIntent.setText(getIntent().getStringExtra("value"));
    }

}
