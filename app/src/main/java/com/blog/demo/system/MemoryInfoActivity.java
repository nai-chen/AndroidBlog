package com.blog.demo.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;

import com.blog.demo.LogTool;

import java.util.List;

public class MemoryInfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        LogTool.logi("MemoryInfoActivity", "totalMem = " + memoryInfo.totalMem);
        LogTool.logi("MemoryInfoActivity", "availMem = " + memoryInfo.availMem);
        LogTool.logi("MemoryInfoActivity", "threshold = " + memoryInfo.threshold);
        LogTool.logi("MemoryInfoActivity", "lowMemory = " + memoryInfo.lowMemory);

        readProcessInfo(am);
    }

    private void readProcessInfo(ActivityManager am) {
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            int[] pids = new int[]{processInfo.pid};
            Debug.MemoryInfo[] debugMemoryInfo = am.getProcessMemoryInfo(pids);

            LogTool.logi("MemoryInfoActivity", "processName = " + processInfo.processName);
            for (Debug.MemoryInfo memoryInfo : debugMemoryInfo) {
                LogTool.logi("MemoryInfoActivity", "dalvikPss = " + memoryInfo.dalvikPss);
                LogTool.logi("MemoryInfoActivity", "nativePss = " + memoryInfo.nativePss);
                LogTool.logi("MemoryInfoActivity", "otherPss = " + memoryInfo.otherPss);
            }
        }

    }

}
