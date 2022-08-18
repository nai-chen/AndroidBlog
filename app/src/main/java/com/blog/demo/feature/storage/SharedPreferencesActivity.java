package com.blog.demo.feature.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class SharedPreferencesActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "SharedPreferencesActivity";

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_storage_shared_preferences);

        findViewById(R.id.btn_write_shared_preferences).setOnClickListener(this);
        findViewById(R.id.btn_read_shared_preferences).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
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
            textView.setText("iValue = " + preference.getInt("iValue", 0) + "\n"
                    + "fValue = " + preference.getFloat("fValue", 0) + "\n"
                    + "bValue = " + preference.getBoolean("bValue", false) + "\n"
                    + "lValue = " + preference.getLong("lValue", 0) + "\n"
                    + "strValue = " + preference.getString("strValue", ""));
        }
    }

}
