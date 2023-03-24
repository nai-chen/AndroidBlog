package com.blog.demo.application.storage.json

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.LogTool
import com.blog.demo.People
import com.blog.demo.R
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class GsonActivity : Activity(), View.OnClickListener {

    private lateinit var textView: TextView
    private var json: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_storage_gson)

        findViewById<Button>(R.id.btn_write_gson).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_gson).setOnClickListener(this)
        findViewById<Button>(R.id.btn_write_gson_bean).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_gson_bean).setOnClickListener(this)

        textView = findViewById(R.id.text_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_write_gson) {
            json = writeObject()
            textView.text = json
        } else if (v.id == R.id.btn_read_gson) {
            val data = readObject(json)
            LogTool.logi("GsonActivity", data.toString())
            textView.text = data.toString()
        } else if (v.id == R.id.btn_write_gson_bean) {
            json = writeJavaBeen()
            textView.text = json
        } else if (v.id == R.id.btn_read_gson_bean) {
            val data = readJavaBeen(json)
            LogTool.logi("GsonActivity", data.toString())
            textView.text = data.toString()
        }
    }

    private fun writeObject(): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("aString", "This is gson string")
        jsonObject.addProperty("aBoolean", true)
        jsonObject.addProperty("aInt", 12)
        jsonObject.addProperty("aDouble", 1.23)
        jsonObject.add("aObject", newPeopleObject(1, "Mike", "ShengZhen", 24))

        val stringJsonArray = JsonArray()
        stringJsonArray.add("football")
        stringJsonArray.add("basketball")
        stringJsonArray.add("volleyball")
        jsonObject.add("aStringArray", stringJsonArray)

        val objectJsonArray = JsonArray()
        objectJsonArray.add(newPeopleObject(2, "Jack", "ShangHai", 26))
        objectJsonArray.add(newPeopleObject(3, "Lily", "BeiJing", 22))
        jsonObject.add("aObjectArray", objectJsonArray)

        return Gson().toJson(jsonObject)
    }

    private fun newPeopleObject(id: Int, name: String, addr: String, age: Int): JsonObject {
        val peopleJsonObject = JsonObject()
        peopleJsonObject.addProperty("id", id)
        peopleJsonObject.addProperty("name", name)
        peopleJsonObject.addProperty("addr", addr)
        peopleJsonObject.addProperty("age", age)
        return peopleJsonObject
    }

    private fun readObject(json: String?): JsonData {
        val jsonObject = JsonParser().parse(json) as JsonObject

        val data = JsonData()
        data.aString = jsonObject["aString"].asString
        data.aBoolean = jsonObject["aBoolean"].asBoolean
        data.aInt = jsonObject["aInt"].asInt
        data.aDouble = jsonObject["aDouble"].asDouble
        data.aObject = getPeople(jsonObject["aObject"].asJsonObject)

        val jsonArray = jsonObject["aStringArray"].asJsonArray
        data.aStringArray = arrayOfNulls(jsonArray.size())
        for (index in 0 until jsonArray.size()) {
            data.aStringArray[index] = jsonArray[index].asString
        }

        val objectArray = jsonObject["aObjectArray"].asJsonArray
        data.aObjectArray = arrayOfNulls(objectArray.size())
        for (index in 0 until objectArray.size()) {
            data.aObjectArray[index] = getPeople(objectArray[index].asJsonObject)
        }

        return data
    }

    private fun getPeople(jsonObject: JsonObject): People {
        val id = jsonObject["id"].asInt
        val name = jsonObject["name"].asString
        val addr = jsonObject["addr"].asString
        val age = jsonObject["age"].asInt
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
        return Gson().toJson(data)
    }

    private fun readJavaBeen(json: String?): JsonData {
        return Gson().fromJson(json, JsonData::class.java)
    }

}