package com.blog.demo.control.pickerview

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.blog.demo.R
import com.contrarywind.view.WheelView
import com.google.gson.Gson
import java.util.*

class OptionsPickerViewActivity : Activity(), View.OnClickListener {

    private var hourList: MutableList<String> = mutableListOf()
    private var minuteList: MutableList<String> = mutableListOf()
    private var secondList: MutableList<String> = mutableListOf()
    private var provinceList: MutableList<String> = mutableListOf()
    private var cityList: MutableList<MutableList<String>> = mutableListOf()
    private var areaList: MutableList<MutableList<MutableList<String>>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_pickview_options)

        findViewById<Button>(R.id.btn_show).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_custom).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_area).setOnClickListener(this)

        for (hour in 0..24) {
            if (hour < 10) {
                hourList.add("0$hour")
            } else {
                hourList.add("$hour")
            }
        }
        for (minute in 0..59) {
            if (minute < 10) {
                minuteList.add("0$minute")
                secondList.add("0$minute")
            } else {
                minuteList.add("$minute")
                secondList.add("$minute")
            }
        }

        var input = assets.open("areadata.json")
        var area: Area = Gson().fromJson(String(input.readBytes()), Area::class.java)
        var data = area.data
        if (data != null) {
            for (province in data) {
                provinceList.add(province.name ?: "")

                var list = province.city
                if (list != null) {
                    var cityNameList = mutableListOf<String>()
                    var cityAreaNameList = mutableListOf<MutableList<String>>()

                    for (city in list) {
                        cityNameList.add(city.name ?: "")
                        var areaNameList = mutableListOf<String>()

                        var list2 = city.area
                        if (list2 != null) {
                            areaNameList.addAll(list2)
                        }
                        cityAreaNameList.add(areaNameList)
                    }
                    cityList.add(cityNameList)
                    areaList.add(cityAreaNameList)
                }

//                cityList.add(province.city_list ?: mutableListOf())
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_show) {
            var optionsPickerView = OptionsPickerBuilder(this) { option1, option2, option3, v ->
                Log.i("OptionsPickerView", "${hourList[option1]}:${minuteList[option2]}:${secondList[option3]}")
            }.build<String>()

            optionsPickerView.setNPicker(hourList, minuteList, secondList)

            var calendar = Calendar.getInstance()
            optionsPickerView.setSelectOptions(calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND))
            optionsPickerView.show()
        } else if (v?.id == R.id.btn_show_custom) {
            showCustomPickView()
        } else if (v?.id == R.id.btn_show_area) {
            var optionsPickerView = OptionsPickerBuilder(this) { option1, option2, option3, v ->
                Log.i("OptionsPickerView", "${provinceList[option1]}:${cityList[option1][option2]}:${areaList[option1][option2][option3]}")
            }.isRestoreItem(true)
                .setItemVisibleCount(11)
                .isDialog(true)
                .build<String>()

            optionsPickerView.setPicker(provinceList, cityList, areaList)
            optionsPickerView.setSelectOptions(2, 5, 5)
            optionsPickerView.show(v)
        }
    }

    private fun showCustomPickView() {
        var optionsPickerView = OptionsPickerBuilder(this) { option1, option2, option3, v ->
            Log.i("OptionsPickerView", "${hourList[option1]}:${minuteList[option2]}:${secondList[option3]}")
        }.setCancelText("Cancel")
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
            .setCyclic(false, true, true)
            .setLabels("时", "分", "秒")
            .isCenterLabel(true)
            .build<String>()

        optionsPickerView.setNPicker(hourList, minuteList, secondList)
        var calendar = Calendar.getInstance()
        optionsPickerView.setSelectOptions(calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND))

        optionsPickerView.show()
    }

}