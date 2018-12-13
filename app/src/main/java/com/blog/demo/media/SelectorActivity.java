package com.blog.demo.media;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.R;

/**
 * Created by cn on 2016/12/28.
 */

public class SelectorActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        findViewById(R.id.btn_multi).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btn_multi) {
//            Toast.makeText(this, "Btn_Multi", Toast.LENGTH_SHORT).show();
//        } else if (v.getId() == R.id.tv_multi) {
//            Toast.makeText(this, "Tv_Multi", Toast.LENGTH_SHORT).show();
//        }

    }
}
