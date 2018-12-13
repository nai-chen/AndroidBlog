package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.ListView;

import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.MessageInfoAdapter.MessageInfo;
import com.blog.demo.R;

import java.util.ArrayList;

/**
 * Created by cn on 2017/3/17.
 */

public class EnvironmentActivity extends Activity {
    public static final String MEDIA_UNKNOWN = "unknown";
    public static final String MEDIA_REMOVED = "removed";
    public static final String MEDIA_UNMOUNTED = "unmounted";
    public static final String MEDIA_CHECKING = "checking";
    public static final String MEDIA_NOFS = "nofs";
    public static final String MEDIA_MOUNTED = "mounted";
    public static final String MEDIA_MOUNTED_READ_ONLY = "mounted_ro";
    public static final String MEDIA_SHARED = "shared";
    public static final String MEDIA_BAD_REMOVAL = "bad_removal";
    public static final String MEDIA_UNMOUNTABLE = "unmountable";
    public static final String MEDIA_EJECTING = "ejecting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        ListView listView = (ListView) findViewById(R.id.id_listview);
        ArrayList<MessageInfo> msgInfos = new ArrayList<MessageInfo>();

        msgInfos.add(new MessageInfo("DataDirectory", Environment.getDataDirectory().getAbsolutePath()));
        msgInfos.add(new MessageInfo("DownloadCacheDirectory", Environment.getDownloadCacheDirectory().getAbsolutePath()));
        msgInfos.add(new MessageInfo("ExternalStorageDirectory", Environment.getExternalStorageDirectory().getAbsolutePath()));
        msgInfos.add(new MessageInfo("RootDirectory", Environment.getRootDirectory().getAbsolutePath()));
        msgInfos.add(new MessageInfo("ExternalStorageState", Environment.getExternalStorageState()));

        msgInfos.add(new MessageInfo("MUSIC",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath()));
        msgInfos.add(new MessageInfo("PODCASTS",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath()));
        msgInfos.add(new MessageInfo("RINGTONES",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath()));
        msgInfos.add(new MessageInfo("ALARMS",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath()));
        msgInfos.add(new MessageInfo("NOTIFICATIONS",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath()));
        msgInfos.add(new MessageInfo("PICTURES",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()));
        msgInfos.add(new MessageInfo("MOVIES",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath()));
        msgInfos.add(new MessageInfo("DOWNLOADS",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()));
        msgInfos.add(new MessageInfo("DCIM",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()));
//        msgInfos.add(new MessageInfo("DOCUMENTS",
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()));

        StatFs fs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        msgInfos.add(new MessageInfo("TotalBytes", fs.getTotalBytes()/1024/1024 + "M"));
        msgInfos.add(new MessageInfo("FreeBytes", fs.getFreeBytes()/1024/1024 + "M"));

        listView.setAdapter(new MessageInfoAdapter(this, msgInfos));
    }
}
