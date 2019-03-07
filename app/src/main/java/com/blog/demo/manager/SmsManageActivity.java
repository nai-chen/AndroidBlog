package com.blog.demo.manager;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

public class SmsManageActivity extends Activity implements View.OnClickListener {
    private static final String ACTION_SEND_SMS = "com.blog.demo.manage.action.SEND_SMS";
    private static final String ACTION_DELIVERY_SMS = "com.blog.demo.manage.action.DELIVERY_SMS";

    private EditText etPhone, etMessage;
    private TextView textView;

    private SmsManager smsManager = SmsManager.getDefault();

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_SEND_SMS)) {
                if (getResultCode() == RESULT_OK) {
                    Toast.makeText(SmsManageActivity.this, "Send SMS", Toast.LENGTH_LONG).show();
                }
            } else if (intent.getAction().equals(ACTION_DELIVERY_SMS)) {
                if (getResultCode() == RESULT_OK) {
                    Toast.makeText(SmsManageActivity.this, "Delivery SMS", Toast.LENGTH_LONG).show();
                }
            } else if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] objs = (Object[]) bundle.get("pdus");
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < objs.length; i++) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) objs[i]);
                        String phone = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getDisplayMessageBody();
                        Toast.makeText(SmsManageActivity.this, phone + ":" + message, Toast.LENGTH_LONG).show();
                        stringBuffer.append(phone + ":" + message + "\n");
                    }
                    textView.setText(stringBuffer.toString());
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager_sms);

        findViewById(R.id.btn_goto_send_sms).setOnClickListener(this);
        findViewById(R.id.btn_send_sms).setOnClickListener(this);
        etPhone = findViewById(R.id.et_phon_number);
        etMessage = findViewById(R.id.et_message);

        textView = findViewById(R.id.text_view);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_goto_send_sms) {
            Uri uri= Uri.parse("smsto:10086");
            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, uri);
            sms_intent.putExtra("sms_body", "HelloWorld");
            startActivity(sms_intent);
        } else if (v.getId() == R.id.btn_send_sms) {
            smsManager.sendTextMessage(etPhone.getText().toString(), null, etMessage.getText().toString(),
                    PendingIntent.getBroadcast(this, 1, new Intent(ACTION_SEND_SMS), 0),
                    PendingIntent.getBroadcast(this, 2, new Intent(ACTION_DELIVERY_SMS), 0));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_SEND_SMS));
        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_DELIVERY_SMS));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }
}
