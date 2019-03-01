package com.blog.demo.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.List;

public class ActivityManagerActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;
    private ActivityManager am;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager_activity);

        findViewById(R.id.btn_show_memory_info).setOnClickListener(this);
        findViewById(R.id.btn_check_app_on_foreground).setOnClickListener(this);
        findViewById(R.id.btn_check_app_on_foreground2).setOnClickListener(this);
        findViewById(R.id.btn_show_running_app_process_info).setOnClickListener(this);


        mTextView = findViewById(R.id.text_view);
        mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_memory_info) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(memoryInfo);
            mTextView.setText("totalMem = " + memoryInfo.totalMem + "\n"
                    + "availMem = " + memoryInfo.availMem + "\n"
                    + "threshold = " + memoryInfo.threshold + "\n"
                    + "lowMemory = " + memoryInfo.lowMemory);
        } else if (v.getId() == R.id.btn_check_app_on_foreground) {
            String pkgName = "com.blog.demo";
            mTextView.setText(checkAppOnForeground(pkgName) + "");
        } else if (v.getId() == R.id.btn_check_app_on_foreground2) {
            String pkgName = "com.blog.demo";
            mTextView.setText(checkAppOnForeground2(pkgName) + "");
        } else if (v.getId() == R.id.btn_show_running_app_process_info) {
            List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
            StringBuffer sBuffer = new StringBuffer();
            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                sBuffer.append(processInfo.processName);
                sBuffer.append(" [");
                for (String pkg : processInfo.pkgList) {
                    sBuffer.append(pkg + ",");
                }
                sBuffer.append("]\n");
            }
            mTextView.setText(sBuffer.toString());
        }

    }

    private boolean checkAppOnForeground(String pkgName) {
        List<ActivityManager.RunningTaskInfo> taskInfos = am.getRunningTasks(1);
        if (taskInfos != null && taskInfos.size() > 0) {
            return taskInfos.get(0).topActivity.getPackageName().equals(pkgName);
        }
        return false;
    }

    private boolean checkAppOnForeground2(String pkgName) {
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.processName.equals(pkgName) && processInfo.importance
                    == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

}
