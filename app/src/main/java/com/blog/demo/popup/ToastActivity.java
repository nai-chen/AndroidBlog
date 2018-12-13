package com.blog.demo.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/2/13.
 */

public class ToastActivity extends Activity implements View.OnClickListener {
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_toast);

        findViewById(R.id.btn_toast_normal).setOnClickListener(this);
        findViewById(R.id.btn_toast_gravity).setOnClickListener(this);
        findViewById(R.id.btn_toast_custom).setOnClickListener(this);
        findViewById(R.id.btn_toast_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toast_normal) {
            Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.btn_toast_gravity) {
            Toast toast = Toast.makeText(this, "自定义位置Toast", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if (v.getId() == R.id.btn_toast_custom) {
            Toast toast = new Toast(this);
            toast.setView(getLayoutInflater().inflate(R.layout.toast_custom_view, null));
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        } else if (v.getId() == R.id.btn_toast_cancel) {
            if (mToast == null) {
                mToast = Toast.makeText(this, "可取消的Toast", Toast.LENGTH_LONG);
                mToast.show();
            } else {
                mToast.cancel();
                mToast = null;
            }
        }
    }
}
