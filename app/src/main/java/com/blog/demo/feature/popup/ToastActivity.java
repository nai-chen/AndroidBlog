package com.blog.demo.feature.popup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

public class ToastActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_popup_toast);

        findViewById(R.id.btn_show_toast).setOnClickListener(this);
        findViewById(R.id.btn_show_center_toast).setOnClickListener(this);
        findViewById(R.id.btn_show_custom_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_toast) {
            Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.btn_show_center_toast) {
            Toast toast = Toast.makeText(this, "自定义位置Toast", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if (v.getId() == R.id.btn_show_custom_toast) {
            Toast toast = new Toast(this);
            toast.setView(getLayoutInflater().inflate(R.layout.toast_custom_view, null));
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
