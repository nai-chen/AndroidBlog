package com.blog.demo.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/2/13.
 */

public class AlertDialogActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert_dialog);

        findViewById(R.id.btn_alert_dialog).setOnClickListener(this);
        findViewById(R.id.btn_alert_button).setOnClickListener(this);
        findViewById(R.id.btn_alert_custom_title).setOnClickListener(this);
        findViewById(R.id.btn_alert_view).setOnClickListener(this);
        findViewById(R.id.btn_alert_item).setOnClickListener(this);
        findViewById(R.id.btn_alert_single_item).setOnClickListener(this);
        findViewById(R.id.btn_alert_multi_item).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_alert_dialog) {
            new AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setIcon(R.drawable.ic_launcher)
                    .setMessage("消息内容")
                    .create().show();
        } else if (v.getId() == R.id.btn_alert_button) {
            new AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setIcon(R.drawable.ic_launcher)
                    .setMessage("消息内容")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();

            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            LogUtil.log("AlertDialogActivity", "onCancel");
                        }
                    }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    LogUtil.log("AlertDialogActivity", "onDismiss");
                }
            });
        } else if (v.getId() == R.id.btn_alert_custom_title) {
            new AlertDialog.Builder(this)
                    .setCustomTitle(getLayoutInflater().inflate(R.layout.toast_custom_view, null))
                    .setMessage("消息内容")
                    .create().show();
        } else if (v.getId() == R.id.btn_alert_view) {
            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setIcon(R.drawable.ic_launcher)
                    .setView(getLayoutInflater().inflate(R.layout.toast_custom_view, null))
                    .create().show();
        } else if (v.getId() == R.id.btn_alert_item) {
            new AlertDialog.Builder(this)
                    .setTitle("城市列表")
                    .setItems(new String[]{"北京", "上海", "广州", "深圳"},
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogUtil.log("AlertDialogActivity", "onClick which = " + which);
                                }
                            }).create().show();
        } else if (v.getId() == R.id.btn_alert_single_item) {
            new AlertDialog.Builder(this)
                    .setTitle("城市单选列表")
                    .setSingleChoiceItems(new String[]{"北京", "上海", "广州", "深圳"}, 1,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogUtil.log("AlertDialogActivity", "onClick which = " + which);
                                }
                            }).create().show();
        } else if (v.getId() == R.id.btn_alert_multi_item) {
            new AlertDialog.Builder(this)
                    .setTitle("城市多选列表")
                    .setMultiChoiceItems(new String[]{"北京", "上海", "广州", "深圳"},
                            new boolean[]{true, false, true, false},
                            new DialogInterface.OnMultiChoiceClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    LogUtil.log("AlertDialogActivity",
                                        "onClick which = " + which + " checked = " + isChecked);

                            }
                    }).create().show();
        }
    }
}
