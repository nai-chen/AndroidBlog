package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;

import com.blog.demo.R;
import com.blog.demo.lib.tools.TimeFormat;
import com.blog.demo.lib.ui.CustomView;

import java.util.Calendar;

/**
 * Created by cn on 2017/4/5.
 */

public class ImportModuleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_import_module);

        CustomView customView = (CustomView) findViewById(R.id.custom_view);
        customView.setTitleTop("时间");
        Calendar calendar = Calendar.getInstance();
        customView.setTitleBottom(TimeFormat.timeToYearMinute(calendar.getTimeInMillis()));
    }

}
