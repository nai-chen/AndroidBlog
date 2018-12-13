package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.MessageInfoAdapter.MessageInfo;
import com.blog.demo.R;

import java.util.ArrayList;

/**
 * Created by cn on 2017/4/1.
 */

public class TelephoneManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        ListView listView = (ListView) findViewById(R.id.id_listview);
        ArrayList<MessageInfo> msgInfos = new ArrayList<MessageInfo>();

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        // 手机信息
        msgInfos.add(new MessageInfo("DeviceId = ", tm.getDeviceId()));
        msgInfos.add(new MessageInfo("SoftwareVersion = ", tm.getDeviceSoftwareVersion()));
        msgInfos.add(new MessageInfo("LineNumber = ", tm.getLine1Number()));

        String phoneTypeStr = "未知";
        int phoneType = tm.getPhoneType();
        switch (phoneType){
            case TelephonyManager.PHONE_TYPE_CDMA:
                phoneTypeStr="CDMA";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                phoneTypeStr="GSM";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                phoneTypeStr="SIP";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneTypeStr="None";
                break;
        }
        msgInfos.add(new MessageInfo("PhoneType = ", phoneTypeStr));

        // 运营商
        msgInfos.add(new MessageInfo("countryIso = ", tm.getNetworkCountryIso()));
        msgInfos.add(new MessageInfo("Operator = ", tm.getNetworkOperator()));
        msgInfos.add(new MessageInfo("OperatorName = ", tm.getNetworkOperatorName()));

        String networkType = "unknow";
        switch(tm.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
                networkType = "GPRS";
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                networkType = "EDGE";
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                networkType = "UMTS";
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                networkType = "CDMA";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                networkType = "EVDO_0";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                networkType = "EVDO_A";
                break;
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                networkType = "1xRTT";
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                networkType = "HSDPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                networkType = "HSUPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                networkType = "HSPA";
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                networkType = "IDEN";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                networkType = "EVDO_B";
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                networkType = "LTE";
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                networkType = "EHRPD";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                networkType = "HSPAP";
                break;
            default:
                networkType = "UNKNOW";
                break;
        }
        msgInfos.add(new MessageInfo("networkType = ", networkType));


        int simState = tm.getSimState();
        switch (simState){
            case TelephonyManager.SIM_STATE_UNKNOWN:
            case TelephonyManager.SIM_STATE_ABSENT:
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                break;
            case TelephonyManager.SIM_STATE_READY:
                msgInfos.add(new MessageInfo("SimCountryIso = ", tm.getSimCountryIso()));
                msgInfos.add(new MessageInfo("SimOperator = ", tm.getSimOperator()));
                msgInfos.add(new MessageInfo("SimOperatorName = ", tm.getSimOperatorName()));
                msgInfos.add(new MessageInfo("SimSerialNumber = ", tm.getSimSerialNumber()));
                break;
        }

        listView.setAdapter(new MessageInfoAdapter(this, msgInfos));
    }
}
