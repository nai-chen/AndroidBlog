package com.blog.demo.component

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.blog.demo.R

class SmsManageActivity : Activity(), View.OnClickListener {

    private val ACTION_SEND_SMS = "com.blog.demo.manage.action.SEND_SMS"
    private val ACTION_DELIVERY_SMS = "com.blog.demo.manage.action.DELIVERY_SMS"

    private lateinit var etPhone: EditText
    private lateinit var etMessage: EditText
    private lateinit var textView: TextView

    private val smsManager = SmsManager.getDefault()

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_SEND_SMS) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this@SmsManageActivity, "Send SMS", Toast.LENGTH_LONG).show()
                }
            } else if (intent.action == ACTION_DELIVERY_SMS) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this@SmsManageActivity, "Delivery SMS", Toast.LENGTH_LONG).show()
                }
            } else if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
                val bundle = intent.extras
                if (bundle != null) {
                    val objs = bundle["pdus"] as Array<ByteArray>
                    val stringBuffer = StringBuffer()
                    for (i in objs.indices) {
                        val smsMessage = SmsMessage.createFromPdu(objs[i])
                        val phone = smsMessage.displayOriginatingAddress
                        val message = smsMessage.displayMessageBody
                        Toast.makeText(this@SmsManageActivity, "$phone:$message", Toast.LENGTH_LONG)
                            .show()
                        stringBuffer.append("$phone:$message\n")
                    }
                    textView.text = stringBuffer.toString()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_sms_manager)

        findViewById<Button>(R.id.btn_goto_send_sms).setOnClickListener(this)
        findViewById<Button>(R.id.btn_send_sms).setOnClickListener(this)

        etPhone = findViewById(R.id.et_phon_number)
        etMessage = findViewById(R.id.et_message)
        textView = findViewById(R.id.text_view)
    }

    override fun onResume() {
        super.onResume()

        registerReceiver(broadcastReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        registerReceiver(broadcastReceiver, IntentFilter(ACTION_SEND_SMS))
        registerReceiver(broadcastReceiver, IntentFilter(ACTION_DELIVERY_SMS))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_goto_send_sms) {
            val uri = Uri.parse("smsto:10086")
            val sms_intent = Intent(Intent.ACTION_SENDTO, uri)
            sms_intent.putExtra("sms_body", "HelloWorld")
            startActivity(sms_intent)
        } else if (v.id == R.id.btn_send_sms) {
            smsManager.sendTextMessage(
                etPhone.text.toString(), null, etMessage.text.toString(),
                PendingIntent.getBroadcast(this, 1, Intent(ACTION_SEND_SMS), 0),
                PendingIntent.getBroadcast(this, 2, Intent(ACTION_DELIVERY_SMS), 0)
            )
        }
    }

}