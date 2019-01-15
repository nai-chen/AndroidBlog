package com.blog.demo.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.util.List;

public class MemoryInfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        TextView textView = findViewById(R.id.text_view);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        String str = "totalMem = " + memoryInfo.totalMem + "\n"
                        + "availMem = " + memoryInfo.availMem + "\n"
                        + "threshold = " + memoryInfo.threshold + "\n"
                        + "lowMemory = " + memoryInfo.lowMemory + "\n";


        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            int[] pids = new int[]{processInfo.pid};
            Debug.MemoryInfo[] debugMemoryInfo = am.getProcessMemoryInfo(pids);

            str += "processName = " + processInfo.processName + "\n";
            for (Debug.MemoryInfo info : debugMemoryInfo) {
                str += "dalvikPss = " + info.dalvikPss + "\n";
                str += "nativePss = " + info.nativePss + "\n";
                str +=  "otherPss = " + info.otherPss + "\n";
            }
        }
        textView.setText(str);

    }

}
