package com.blog.demo.control.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.R;

/**
 * Created by cn on 2017/2/9.
 */

public class RelativeLayoutActivity extends Activity implements View.OnClickListener {

    private View mViewAlign, mViewPosition, mViewAlignParent, mViewCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_relative_layout);

        findViewById(R.id.btn_align).setOnClickListener(this);
        findViewById(R.id.btn_position).setOnClickListener(this);
        findViewById(R.id.btn_align_parent).setOnClickListener(this);
        findViewById(R.id.btn_center).setOnClickListener(this);

        mViewAlign = findViewById(R.id.view_layout_align);
        mViewPosition = findViewById(R.id.view_layout_position);
        mViewAlignParent = findViewById(R.id.view_layout_align_parent);
        mViewCenter = findViewById(R.id.view_layout_center);
    }

    @Override
    public void onClick(View v) {
        mViewAlign.setVisibility(View.GONE);
        mViewPosition.setVisibility(View.GONE);
        mViewAlignParent.setVisibility(View.GONE);
        mViewCenter.setVisibility(View.GONE);
        switch (v.getId()) {
            case R.id.btn_align:
                mViewAlign.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_position:
                mViewPosition.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_align_parent:
                mViewAlignParent.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_center:
                mViewCenter.setVisibility(View.VISIBLE);
                break;
        }
    }

}
