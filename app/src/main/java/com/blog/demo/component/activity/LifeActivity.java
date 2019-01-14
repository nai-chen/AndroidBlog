package com.blog.demo.component.activity;

import android.os.Bundle;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class LifeActivity extends AbstractLifeActivity {
    private final static String LOG_TAG = "LifeActivity";

    private int iValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogTool.logi(LOG_TAG, "i = " + iValue);

        setContentView(R.layout.activity_component_life);
        findViewById(R.id.btn_change_value).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iValue += 10;
                LogTool.logi(LOG_TAG, "i = " + iValue);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogTool.logi(LOG_TAG, "onSaveInstanceState");

        outState.putInt("key1", 23);
        outState.putInt("key2", iValue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogTool.logi(LOG_TAG, "onRestoreInstanceState");

        LogTool.logi(LOG_TAG, "key1 = " + savedInstanceState.getInt("key1"));
        LogTool.logi(LOG_TAG, "key2 = " + savedInstanceState.getInt("key2"));
    }

    @Override
    protected String getLogTag() {
        return LOG_TAG;
    }

}
