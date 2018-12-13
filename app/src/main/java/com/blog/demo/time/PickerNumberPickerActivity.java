package com.blog.demo.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cn on 2017/3/28.
 */

public class PickerNumberPickerActivity extends Activity {
    private TextView mTvNUmberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picker_number_picker);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        mTvNUmberPicker = (TextView) findViewById(R.id.tv_number_picker);
        numberPicker.setMinValue(10);
        numberPicker.setMaxValue(20);
        numberPicker.setValue(11);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setNumberPicker(newVal);
            }
        });
        setNumberPicker(11);
    }

    private void setNumberPicker(int value) {
        mTvNUmberPicker.setText("当前数字为：" + Integer.toString(value));
    }

}
