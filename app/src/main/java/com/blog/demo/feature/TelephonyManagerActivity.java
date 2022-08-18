package com.blog.demo.feature;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.blog.demo.R;

public class TelephonyManagerActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);
        textView = findViewById(R.id.text_view);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            readTelephonyManager();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
        }
    }

    private void readTelephonyManager() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        textView.setText("deviceId = " + tm.getDeviceId() + "\n"
                + "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n"
                + "Line1Number = " + tm.getLine1Number() + "\n"
                + "PhoneType = " + getPhoneType(tm.getPhoneType()) + "\n"
                + "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n"
                + "NetworkOperator = " + tm.getNetworkOperator() + "\n"
                + "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n"
                + "NetworkType = " + getNetworkType(tm.getNetworkType()) + "\n"
                + "SimState = " + getSimState(tm.getSimState()) + "\n"
                + "SimCountryIso = " + tm.getSimCountryIso() + "\n"
                + "SimOperator = " + tm.getSimOperator() + "\n"
                + "SimOperatorName = " + tm.getSimOperatorName() + "\n"
                + "SimSerialNumber = " + tm.getSimSerialNumber());
    }

    private String getPhoneType(int phoneType) {
        if (phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
            return "CDMA";
        } else if (phoneType == TelephonyManager.PHONE_TYPE_GSM) {
            return "GSM";
        } else if (phoneType == TelephonyManager.PHONE_TYPE_SIP) {
            return "SIP";
        } else {
            return "NONE";
        }
    }

    private String getNetworkType(int networkType) {
        if (networkType == TelephonyManager.NETWORK_TYPE_GPRS) {
            return "GPRS";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EDGE) {
            return "EDGE";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_UMTS) {
            return "UMTS";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_CDMA) {
            return "CDMA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_0) {
            return "EVDO_0";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_A) {
            return "EVDO_A";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_1xRTT) {
            return "1xRTT";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSDPA) {
            return "HSDPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSUPA) {
            return "HSUPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSPA) {
            return "HSPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_IDEN) {
            return "IDEN";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
            return "EVDO_B";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_LTE) {
            return "LTE";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EHRPD) {
            return "EHRPD";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSPAP) {
            return "HSPAP";
        } else {
            return "UNKNOWN";
        }
    }

    private String getSimState(int simState) {
        if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            return "ABSENT";
        } else if (simState == TelephonyManager.SIM_STATE_PIN_REQUIRED) {
            return "PIN_REQUIRED";
        } else if (simState == TelephonyManager.SIM_STATE_PUK_REQUIRED) {
            return "PUK_REQUIRED";
        } else if (simState == TelephonyManager.SIM_STATE_NETWORK_LOCKED) {
            return "NETWORK_LOCKED";
        } else if (simState == TelephonyManager.SIM_STATE_READY) {
            return "READY";
        } else {
            return "UNKNOWN";
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readTelephonyManager();
        } else {
            finish();
        }
    }
}
