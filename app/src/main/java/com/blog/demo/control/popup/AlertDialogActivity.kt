package com.blog.demo.control.popup

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R

class AlertDialogActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_popup_alert_dialog)

        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_custom_dialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_button_dialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_item_dialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_single_dialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_multi_dialog).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_show_dialog) {
            AlertDialog.Builder(this, R.style.AppTheme_Dialog)
                .setTitle("标题")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("消息内容")
                .create().show()
        } else if (v.id == R.id.btn_show_custom_dialog) {
            AlertDialog.Builder(this)
                .setCustomTitle(layoutInflater.inflate(R.layout.layout_dialog_title, null))
                .setView(R.layout.layout_dialog_view)
                .create().show()
        } else if (v.id == R.id.btn_show_button_dialog) {
            AlertDialog.Builder(this)
                .setTitle("标题")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("消息内容")
                .setPositiveButton("确认") { _, _ -> }
                .setNegativeButton("取消") { _, _ -> }
                .setNeutralButton("忽略") { _, _ -> }
                .create().show()
        } else if (v.id == R.id.btn_show_item_dialog) {
            AlertDialog.Builder(this)
                .setTitle("城市列表")
                .setItems(R.array.city_list) { _, _ -> }
                .create().show()
        } else if (v.id == R.id.btn_show_single_dialog) {
            AlertDialog.Builder(this)
                .setTitle("城市单选列表")
                .setSingleChoiceItems(R.array.city_list, 1) { _, _ -> }
                .create().show()
        } else if (v.id == R.id.btn_show_multi_dialog) {
            AlertDialog.Builder(this)
                .setTitle("城市多选列表")
                .setMultiChoiceItems(R.array.city_list, booleanArrayOf(true, false, true, false)) { _, _, _ -> }
                .create().show()
        }
    }

}