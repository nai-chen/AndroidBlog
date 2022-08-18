package com.blog.demo.feature;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Debug;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

import java.util.List;

public class ActivityManagerActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;
    private ActivityManager am;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feature_activity_manager);

        findViewById(R.id.btn_show_memory_info).setOnClickListener(this);
        findViewById(R.id.btn_check_app_on_foreground).setOnClickListener(this);
        findViewById(R.id.btn_check_app_on_foreground2).setOnClickListener(this);
        findViewById(R.id.btn_check_debug_memory_info).setOnClickListener(this);

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
        } else if (v.getId() == R.id.btn_check_debug_memory_info) {
            getDebugMemoryInfo();
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
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo.processName.equals(pkgName) && processInfo.importance
                    == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    private void getDebugMemoryInfo() {
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        StringBuffer sBuffer = new StringBuffer();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            int[] pids = new int[]{processInfo.pid};
            Debug.MemoryInfo[] debugMemoryInfo = am.getProcessMemoryInfo(pids);

            sBuffer.append("processName = " + processInfo.processName + "\n");
            for (Debug.MemoryInfo info : debugMemoryInfo) {
                sBuffer.append("dalvikPss = " + info.dalvikPss + "\n");
                sBuffer.append("nativePss = " + info.nativePss + "\n");
                sBuffer.append("otherPss = " + info.otherPss + "\n");
            }
        }
        mTextView.setText(sBuffer.toString());
    }

}
