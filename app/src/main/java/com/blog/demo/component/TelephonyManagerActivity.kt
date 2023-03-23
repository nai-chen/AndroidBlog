package com.blog.demo.component

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.blog.demo.R

class TelephonyManagerActivity : Activity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        textView = findViewById(R.id.text_view)
    }

    private fun readTelephonyManager() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            textView.text = """
            deviceId = ${tm.deviceId}
            DeviceSoftwareVersion = ${tm.deviceSoftwareVersion}
            Line1Number = ${tm.line1Number}
            PhoneType = ${getPhoneType(tm.phoneType)}
            NetworkCountryIso = ${tm.networkCountryIso}
            NetworkOperator = ${tm.networkOperator}
            NetworkOperatorName = ${tm.networkOperatorName}
            NetworkType = ${getNetworkType(tm.networkType)}
            SimState = ${getSimState(tm.simState)}
            SimCountryIso = ${tm.simCountryIso}
            SimOperator = ${tm.simOperator}
            SimOperatorName = ${tm.simOperatorName}
            SimSerialNumber = ${tm.simSerialNumber}
            """.trimIndent()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                1000
            )
        }
    }

    private fun getPhoneType(phoneType: Int): String {
        return if (phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
            "CDMA"
        } else if (phoneType == TelephonyManager.PHONE_TYPE_GSM) {
            "GSM"
        } else if (phoneType == TelephonyManager.PHONE_TYPE_SIP) {
            "SIP"
        } else {
            "NONE"
        }
    }

    private fun getNetworkType(networkType: Int): String {
        return if (networkType == TelephonyManager.NETWORK_TYPE_GPRS) {
            "GPRS"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EDGE) {
            "EDGE"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_UMTS) {
            "UMTS"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_CDMA) {
            "CDMA"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_0) {
            "EVDO_0"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_A) {
            "EVDO_A"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_1xRTT) {
            "1xRTT"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSDPA) {
            "HSDPA"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSUPA) {
            "HSUPA"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSPA) {
            "HSPA"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_IDEN) {
            "IDEN"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
            "EVDO_B"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_LTE) {
            "LTE"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EHRPD) {
            "EHRPD"
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSPAP) {
            "HSPAP"
        } else {
            "UNKNOWN"
        }
    }

    private fun getSimState(simState: Int): String {
        return if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            "ABSENT"
        } else if (simState == TelephonyManager.SIM_STATE_PIN_REQUIRED) {
            "PIN_REQUIRED"
        } else if (simState == TelephonyManager.SIM_STATE_PUK_REQUIRED) {
            "PUK_REQUIRED"
        } else if (simState == TelephonyManager.SIM_STATE_NETWORK_LOCKED) {
            "NETWORK_LOCKED"
        } else if (simState == TelephonyManager.SIM_STATE_READY) {
            "READY"
        } else {
            "UNKNOWN"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readTelephonyManager()
        } else {
            finish()
        }
    }

}