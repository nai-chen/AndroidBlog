package com.blog.demo.control.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.demo.R;

public class AlertDialogActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_popup_alert_dialog);

        findViewById(R.id.btn_show_dialog).setOnClickListener(this);
        findViewById(R.id.btn_show_custom_dialog).setOnClickListener(this);
        findViewById(R.id.btn_show_button_dialog).setOnClickListener(this);
        findViewById(R.id.btn_show_item_dialog).setOnClickListener(this);
        findViewById(R.id.btn_show_single_dialog).setOnClickListener(this);
        findViewById(R.id.btn_show_multi_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_dialog) {
            new AlertDialog.Builder(this, R.style.AppTheme_Dialog)
                    .setTitle("标题")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("消息内容")
                    .create().show();
        } else if (v.getId() == R.id.btn_show_custom_dialog) {
            new AlertDialog.Builder(this)
                    .setCustomTitle(getLayoutInflater().inflate(R.layout.layout_dialog_title, null))
                    .setView(R.layout.layout_dialog_view)
                    .create().show();
        } else if (v.getId() == R.id.btn_show_button_dialog) {
            new AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setIcon(R.mipmap.ic_launcher)
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
        } else if (v.getId() == R.id.btn_show_item_dialog) {
            new AlertDialog.Builder(this)
                    .setTitle("城市列表")
                    .setItems(R.array.city_list, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
        } else if (v.getId() == R.id.btn_show_single_dialog) {
            new AlertDialog.Builder(this)
                    .setTitle("城市单选列表")
                    .setSingleChoiceItems(R.array.city_list, 1, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
        } else if (v.getId() == R.id.btn_show_multi_dialog) {
            new AlertDialog.Builder(this)
                    .setTitle("城市多选列表")
                    .setMultiChoiceItems(R.array.city_list, new boolean[]{true, false, true, false},
                            new DialogInterface.OnMultiChoiceClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                }
                            }).create().show();
        }
    }

}
