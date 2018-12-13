package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by cn on 2017/6/16.
 */

public class UmengActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_umeng);

        findViewById(R.id.btn_click).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_click) {
            MobclickAgent.onEvent(this, "btn_click");
        }
    }
}
