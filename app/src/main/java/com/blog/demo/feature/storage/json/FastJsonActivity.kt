package com.blog.demo.feature.storage.json

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.blog.demo.LogTool
import com.blog.demo.People
import com.blog.demo.R

class FastJsonActivity : Activity(), View.OnClickListener {

    private lateinit var textView: TextView
    private var json: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_storage_fast_json)

        findViewById<Button>(R.id.btn_write_fast_json).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_fast_json).setOnClickListener(this)
        findViewById<Button>(R.id.btn_write_fast_json_bean).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_fast_json_bean).setOnClickListener(this)

        textView = findViewById(R.id.text_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_write_fast_json) {
            json = writeObject()
            textView.text = json
        } else if (v.id == R.id.btn_read_fast_json) {
            val data = readObject(json)
            LogTool.logi("FastJsonActivity", data.toString())
            textView.text = data.toString()
        } else if (v.id == R.id.btn_write_fast_json_bean) {
            json = writeJavaBeen()
            textView.text = json
        } else if (v.id == R.id.btn_read_fast_json_bean) {
            val data = readJavaBeen(json)
            LogTool.logi("FastJsonActivity", data.toString())
            textView.text = data.toString()
        }
    }


    private fun writeObject(): String? {
        val jsonObject = JSONObject()
        jsonObject["aString"] = "This is fastJson string"
        jsonObject["aBoolean"] = true
        jsonObject["aInt"] = 12
        jsonObject["aDouble"] = 1.23
        jsonObject["aObject"] = newPeopleObject(1, "Mike", "ShengZhen", 24)

        val stringJsonArray = JSONArray()
        stringJsonArray.add("football")
        stringJsonArray.add("basketball")
        stringJsonArray.add("volleyball")
        jsonObject["aStringArray"] = stringJsonArray

        val objectJsonArray = JSONArray()
        objectJsonArray.add(newPeopleObject(2, "Jack", "ShangHai", 26))
        objectJsonArray.add(newPeopleObject(3, "Lily", "BeiJing", 22))
        jsonObject["aObjectArray"] = objectJsonArray

        return jsonObject.toJSONString()
    }

    private fun newPeopleObject(id: Int, name: String, addr: String, age: Int): JSONObject {
        val peopleJsonObject = JSONObject()
        peopleJsonObject["id"] = id
        peopleJsonObject["name"] = name
        peopleJsonObject["addr"] = addr
        peopleJsonObject["age"] = age
        return peopleJsonObject
    }

    private fun readObject(json: String?): JsonData {
        val jsonObject = JSONObject.parseObject(json)

        val data = JsonData()
        data.aString = jsonObject.getString("aString")
        data.aBoolean = jsonObject.getBooleanValue("aBoolean")
        data.aInt = jsonObject.getIntValue("aInt")
        data.aDouble = jsonObject.getDoubleValue("aDouble")
        data.aObject = getPeople(jsonObject.getJSONObject("aObject"))

        val jsonArray = jsonObject.getJSONArray("aStringArray")
        data.aStringArray = arrayOfNulls(jsonArray.size)
        for (index in jsonArray.indices) {
            data.aStringArray[index] = jsonArray.getString(index)
        }

        val objectArray = jsonObject.getJSONArray("aObjectArray")
        data.aObjectArray = arrayOfNulls(objectArray.size)
        for (index in objectArray.indices) {
            data.aObjectArray[index] = getPeople(objectArray.getJSONObject(index))
        }

        return data
    }

    private fun getPeople(jsonObject: JSONObject): People {
        val id = jsonObject.getIntValue("id")
        val name = jsonObject.getString("name")
        val addr = jsonObject.getString("addr")
        val age = jsonObject.getIntValue("age")
        return People(id, name, addr, age)
    }

    private fun writeJavaBeen(): String? {
        val data = JsonData()
        data.aString = "This is gson string"
        data.aBoolean = true
        data.aInt = 12
        data.aDouble = 1.23
        data.aObject = People(1, "Mike", "ShengZhen", 24)
        data.aStringArray = arrayOf("football", "basketball", "volleyball")
        data.aObjectArray = arrayOf(
            People(2, "Jack", "ShangHai", 26),
            People(3, "Lily", "BeiJing", 22)
        )
        return JSON.toJSONString(data)
    }

    private fun readJavaBeen(json: String?): JsonData {
        return JSON.parseObject(json, JsonData::class.java)
    }

}