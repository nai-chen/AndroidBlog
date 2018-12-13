package com.blog.demo.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cn on 2017/3/28.
 */

public class PickerDatePickerActivity extends Activity {
    private TextView mTvDatePicker;
    private Calendar mCalendar = Calendar.getInstance();
    private DateFormat mDateFormatYear = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picker_date_picker);

        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        mTvDatePicker = (TextView) findViewById(R.id.tv_date_picker);

//        int year = mCalendar.get(Calendar.YEAR);
//        int monthOfYear = mCalendar.get(Calendar.MONTH);
//        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        int year = 2000;
        int monthOfYear = 0;
        int dayOfMonth = 1;
        setDatePicker(year, monthOfYear, dayOfMonth);

        // 设置初始值和监听器
        datePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setDatePicker(year, monthOfYear, dayOfMonth);
            }
        });

    }

    private void setDatePicker(int year, int monthOfYear, int dayOfMonth) {
        LogUtil.log("PickerActivity", "year = " + year + ", monthOfYear = " + monthOfYear + ", dayOfMonth = " + dayOfMonth);
        mCalendar.set(year, monthOfYear, dayOfMonth);

        mTvDatePicker.setText(mDateFormatYear.format(mCalendar.getTime()));
    }

}
