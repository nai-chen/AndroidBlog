package com.blog.demo.fragment;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blog.demo.BuildConfig;
import com.blog.demo.R;

/**
 * Created by cn on 2017/1/24.
 */

public class GradleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gradle);

        CheckBox cb = (CheckBox) findViewById(R.id.cb_log);
        cb.setChecked(BuildConfig.Log);

        TextView tvChannel = (TextView) findViewById(R.id.tv_channel);
        tvChannel.setText(getChannel() + getPackageInfo());
    }

    public String getChannel() {
        try {
            ApplicationInfo applicationInfo = getApplicationContext().getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);

            Object value = applicationInfo.metaData.get("UMENG_CHANNEL");
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private String getPackageInfo() {
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return " versionName = " + pinfo.versionName + " versionCode = " + pinfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";

    }

}
