package com.blog.demo.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Debug;
import android.widget.ListView;

import com.blog.demo.LogUtil;
import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.MessageInfoAdapter.MessageInfo;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/15.
 */

public class MeminfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        ListView lvMemoryInfo = (ListView) findViewById(R.id.id_listview);
        List<MessageInfo> infoList = new ArrayList<MessageInfo>();
        infoList.add(new MessageInfo("availMem = ", memoryInfo.availMem + ""));
        infoList.add(new MessageInfo("totalMem = ", memoryInfo.totalMem + ""));
        infoList.add(new MessageInfo("lowMemory = ", memoryInfo.lowMemory + ""));
        infoList.add(new MessageInfo("threshold = ", memoryInfo.threshold + ""));

        infoList.add(new MessageInfo("Pid", "ProcessName", "TotalPss"));

        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        for (int index = 0; index < processInfos.size(); index++) {
            ActivityManager.RunningAppProcessInfo info = processInfos.get(index);
            int[] pids = new int[]{info.pid};
            Debug.MemoryInfo[] debugMemoryInfos = am.getProcessMemoryInfo(pids);
            if (debugMemoryInfos.length == 0) {
                LogUtil.log("MemoryInfo", "pid = " + info.pid
                    + " ProcessName = " + info.processName + " has no memoryInfo");
            } else {
                for (Debug.MemoryInfo debugInfo : debugMemoryInfos) {
                    LogUtil.log("MemoryInfo", "pid = " + info.pid
                            + " ProcessName = " + info.processName
                            + " Debug.MemoryInfo = " + debugInfo.getTotalPss());
                    infoList.add(new MessageInfo("" + info.pid, info.processName,
                            "" + debugInfo.getTotalPss()));
                }
            }
        }

        lvMemoryInfo.setAdapter(new MessageInfoAdapter(this, infoList));
    }

}
