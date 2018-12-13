package com.blog.demo.control.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.R;

/**
 * Created by cn on 2017/2/9.
 */

public class LinearLayoutActivity extends Activity implements View.OnClickListener {
    private View mViewOrientation, mViewWeight, mViewGravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_linear_layout);
        findViewById(R.id.btn_orientation).setOnClickListener(this);
        findViewById(R.id.btn_weight).setOnClickListener(this);
        findViewById(R.id.btn_gravity).setOnClickListener(this);

        mViewOrientation = findViewById(R.id.view_layout_orientation);
        mViewWeight = findViewById(R.id.view_layout_weight);
        mViewGravity = findViewById(R.id.view_layout_gravity);
    }

    @Override
    public void onClick(View v) {
        mViewOrientation.setVisibility(View.GONE);
        mViewWeight.setVisibility(View.GONE);
        mViewGravity.setVisibility(View.GONE);

        switch (v.getId()) {
            case R.id.btn_orientation:
                mViewOrientation.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_weight:
                mViewWeight.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_gravity:
                mViewGravity.setVisibility(View.VISIBLE);
                break;
        }
    }
}
