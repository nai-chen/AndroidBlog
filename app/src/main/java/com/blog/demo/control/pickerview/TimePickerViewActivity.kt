package com.blog.demo.control.pickerview

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.blog.demo.R
import com.contrarywind.view.WheelView
import java.text.SimpleDateFormat
import java.util.*

class TimePickerViewActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_pickview_time)

        findViewById<Button>(R.id.btn_show).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_custom).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_show) {
            var timePickerView = TimePickerBuilder(this) { date, v ->
                var sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
                Log.i("TimePickerViewActivity", sdf.format(date))
            }.build()

            timePickerView.show()
        } else if (v?.id == R.id.btn_show_custom) {
            showCustomPickView()
        }
    }

    private fun showCustomPickView() {
        var timePickerView = TimePickerBuilder(this) { date, v ->
            var sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            Log.i("TimePickerViewActivity", sdf.format(date))
        }.setType(booleanArrayOf(true, true, true, true, false, false))
            .setCancelText("Cancel")
            .setCancelColor(Color.GRAY)
            .setSubmitText("Confirm")
            .setSubmitColor(Color.MAGENTA)
            .setSubCalSize(15)
            .setTitleText("Title")
            .setTitleColor(Color.RED)
            .setTitleSize(25)
            .setTitleBgColor(Color.BLACK)
            .setBgColor(Color.BLACK)
            .setContentTextSize(20)
            .setItemVisibleCount(11)
            .setTextColorCenter(Color.RED)
            .setTextColorOut(Color.MAGENTA)
            .setOutSideColor(Color.GRAY)
            .setDividerColor(Color.CYAN)
            .setDividerType(WheelView.DividerType.WRAP)
            .setLineSpacingMultiplier(2.5f)
            .isCyclic(true)
            .isCenterLabel(true)
            .build()

        timePickerView.show()
    }

}