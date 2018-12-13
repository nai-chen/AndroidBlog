package com.blog.demo.system;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.TextView;

import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.MessageInfoAdapter.MessageInfo;
import com.blog.demo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/13.
 */

public class BuildActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);
        ListView lvBuild = (ListView) findViewById(R.id.id_listview);
        List<MessageInfo> infoList = new ArrayList<MessageInfo>();
        infoList.add(new MessageInfo("ID", Build.ID));
        infoList.add(new MessageInfo("DISPLAY", Build.DISPLAY));
        infoList.add(new MessageInfo("PRODUCT", Build.PRODUCT));
        infoList.add(new MessageInfo("DEVICE", Build.DEVICE));
        infoList.add(new MessageInfo("BOARD", Build.BOARD));
        infoList.add(new MessageInfo("MANUFACTURER", Build.MANUFACTURER));
        infoList.add(new MessageInfo("MODEL", Build.MODEL));
        infoList.add(new MessageInfo("HARDWARE", Build.HARDWARE));
        infoList.add(new MessageInfo("SERIAL", Build.SERIAL));
        infoList.add(new MessageInfo("TYPE", Build.TYPE));
        infoList.add(new MessageInfo("TAGS", Build.TAGS));
        infoList.add(new MessageInfo("FINGERPRINT", Build.FINGERPRINT));
        infoList.add(new MessageInfo("USER", Build.USER));
        infoList.add(new MessageInfo("HOST", Build.HOST));
        infoList.add(new MessageInfo("INCREMENTAL", Build.VERSION.INCREMENTAL));
        infoList.add(new MessageInfo("RELEASE", Build.VERSION.RELEASE));
        infoList.add(new MessageInfo("SDK_INT", ""+Build.VERSION.SDK_INT));
        infoList.add(new MessageInfo("CODENAME", Build.VERSION.CODENAME));

        int i =
                Build.VERSION_CODES.BASE +
                Build.VERSION_CODES.BASE_1_1 + // 1.1
                Build.VERSION_CODES.CUPCAKE + // 1.5
                Build.VERSION_CODES.DONUT + // 1.6                     4
                Build.VERSION_CODES.ECLAIR + // 2.0                    5
                Build.VERSION_CODES.ECLAIR_0_1 + // 2.0.1              6
                Build.VERSION_CODES.ECLAIR_MR1 + // 2.1                7
                Build.VERSION_CODES.FROYO + // 2.2                     8
                Build.VERSION_CODES.GINGERBREAD + // 2.3               9
                Build.VERSION_CODES.GINGERBREAD_MR1 + // 2.3.3         10
                Build.VERSION_CODES.HONEYCOMB + // 3.0                 11
                Build.VERSION_CODES.HONEYCOMB_MR1 + // 3.1             12
                Build.VERSION_CODES.HONEYCOMB_MR2 + // 3.2             13
                Build.VERSION_CODES.ICE_CREAM_SANDWICH + // 4.0        14
                Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 + // 4.0.3  15
                Build.VERSION_CODES.JELLY_BEAN + // 4.1                16
                Build.VERSION_CODES.JELLY_BEAN_MR1 + // 4.2            17
                Build.VERSION_CODES.JELLY_BEAN_MR2 + // 4.3            18
                Build.VERSION_CODES.KITKAT + // 4.3                    19
                Build.VERSION_CODES.KITKAT_WATCH + // 4.4W             20
                Build.VERSION_CODES.LOLLIPOP + // 5.0
                        Build.VERSION_CODES.LOLLIPOP_MR1 + //5.1
                        Build.VERSION_CODES.M; // 6.0
//                        Build.VERSION_CODES.N + // 7.0
//                        Build.VERSION_CODES.N_MR1; // 7.1
        lvBuild.setAdapter(new MessageInfoAdapter(this, infoList));
    }
}
