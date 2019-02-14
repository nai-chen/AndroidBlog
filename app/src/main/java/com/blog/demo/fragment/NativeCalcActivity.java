package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blog.demo.R;

public class NativeCalcActivity extends Activity implements View.OnClickListener {

    private TextView mTvResult;
    private Calc mCalc = new Calc();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_native_calc);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_subtract).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);

        mTvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            mTvResult.setText(mCalc.add(18, 6) + "");
        } else if (v.getId() == R.id.btn_subtract) {
            mTvResult.setText(mCalc.subtract(18, 6) + "");
        } else if (v.getId() == R.id.btn_multiply) {
            mTvResult.setText(mCalc.multiply(18, 6) + "");
        } else if (v.getId() == R.id.btn_divide) {
            mTvResult.setText(mCalc.divide(18, 6) + "");
        }
    }
}
