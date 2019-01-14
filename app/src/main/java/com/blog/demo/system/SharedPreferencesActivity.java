package com.blog.demo.system;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class SharedPreferencesActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "SharedPreferencesActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_shared_preferences);

        findViewById(R.id.btn_write_shared_preferences).setOnClickListener(this);
        findViewById(R.id.btn_read_shared_preferences).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write_shared_preferences) {
            SharedPreferences preference = getSharedPreferences("people", MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();
            editor.putInt("iValue", 10);
            editor.putFloat("fValue", 1.45f);
            editor.putBoolean("bValue", true);
            editor.putLong("lValue", 123);
            editor.putString("strValue", "This is a string");
            editor.commit();
        } else if (v.getId() == R.id.btn_read_shared_preferences) {
            SharedPreferences preference = getSharedPreferences("people", MODE_PRIVATE);
            LogTool.logi(LOG_TAG, "iValue = " + preference.getInt("iValue", 0));
            LogTool.logi(LOG_TAG, "fValue = " + preference.getFloat("fValue", 0));
            LogTool.logi(LOG_TAG, "bValue = " + preference.getBoolean("bValue", false));
            LogTool.logi(LOG_TAG, "lValue = " + preference.getLong("lValue", 0));
            LogTool.logi(LOG_TAG, "strValue = " + preference.getString("strValue", ""));
        }
    }

}
