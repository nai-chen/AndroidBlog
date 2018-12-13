package com.blog.demo.application;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blog.demo.LogUtil;
import com.blog.demo.R;
import com.blog.demo.Util;

/**
 * Created by cn on 2017/6/27.
 */

public class SipActivity extends Activity implements View.OnClickListener {
    private final static String SERVER_DOMAIN = "192.168.4.58";
    private EditText mEtUsername, mEtPassword;
    private SipManager mSipManager;
    private SipProfile mSipProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sip);

        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);

        findViewById(R.id.btn_submit).setOnClickListener(this);

        // 是否支持VOIP通话
        boolean supported = SipManager.isVoipSupported(this);
        if (!supported) {
            Util.showToastMessage(this, "不支持VOIP通话");
        }

        // 是否支持SIP的Api
        supported = SipManager.isApiSupported(this);
        if (!supported) {
            Util.showToastMessage(this, "不支持SIP的Api");
        }

        mSipManager = SipManager.newInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                login(mEtUsername.getText().toString(), mEtPassword.getText().toString(), SERVER_DOMAIN);
                break;
        }
    }


    private void login(String username, String password, String serverDomain) {
        try {
            SipProfile.Builder builder = new SipProfile.Builder(username, serverDomain);
            builder.setPassword("123456");
            mSipProfile = builder.build();

            Intent intent = new Intent();
            intent.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
            mSipManager.open(mSipProfile, pendingIntent,
//                    null);
//            mSipManager.setRegistrationListener(mSipProfile.getUriString(),
            new SipRegistrationListener() {
                @Override
                public void onRegistering(String localProfileUri) {
                    LogUtil.log("SipActivity", "onRegistering " + localProfileUri);
                }

                @Override
                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    LogUtil.log("SipActivity", "onRegistrationDone " + localProfileUri);
                }

                @Override
                public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                    LogUtil.log("onRegistrationFailed", "onRegistrationFailed " + localProfileUri + " errorCode " + errorCode);
                }
            });
        } catch (Exception e) {
            LogUtil.log("SipActivity", e.getMessage());
            Util.showToastMessage(this, "登录失败");
        }
    }

    @Override
    protected void onDestroy() {
        if (mSipManager == null)
            return;

        if (mSipProfile != null) {
            try {
                mSipManager.close(mSipProfile.getUriString());
            } catch (SipException e) {
            }
        }
        super.onDestroy();
    }
}
