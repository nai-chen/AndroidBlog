package com.blog.demo.application.storage.json

import android.app.Activity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.LogTool.logi
import com.blog.demo.People
import com.blog.demo.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JsonActivity : Activity(), View.OnClickListener {

    private lateinit var textView: TextView
    private var json: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_storage_json)

        findViewById<Button>(R.id.btn_write_json).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_json).setOnClickListener(this)

        textView = findViewById(R.id.text_view)
        textView.movementMethod = ScrollingMovementMethod.getInstance()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_write_json) {
            try {
                json = writeObject()
                textView.text = json
            } catch (e: JSONException) {
            }
        } else if (v.id == R.id.btn_read_json) {
            try {
                val data = readObject(json)
                logi("JsonActivity", data.toString())
                textView.text = data.toString()
            } catch (e: JSONException) {
            }
        }
    }

    @Throws(JSONException::class)
    private fun writeObject(): String? {
        val jsonObject = JSONObject()
        jsonObject.put("aString", "This is json string")
        jsonObject.put("aBoolean", true)
        jsonObject.put("aInt", 12)
        jsonObject.put("aDouble", 1.23)
        jsonObject.put("aObject", newPeopleObject(1, "Mike", "ShengZhen", 24))

        val stringJsonArray = JSONArray()
        stringJsonArray.put("football")
        stringJsonArray.put("basketball")
        stringJsonArray.put("volleyball")
        jsonObject.put("aStringArray", stringJsonArray)

        val objectJsonArray = JSONArray()
        objectJsonArray.put(newPeopleObject(2, "Jack", "ShangHai", 26))
        objectJsonArray.put(newPeopleObject(3, "Lily", "BeiJing", 22))
        jsonObject.put("aObjectArray", objectJsonArray)

        return jsonObject.toString(4)
    }

    @Throws(JSONException::class)
    private fun newPeopleObject(id: Int, name: String, addr: String, age: Int): JSONObject {
        val peopleJsonObject = JSONObject()
        peopleJsonObject.put("id", id)
        peopleJsonObject.put("name", name)
        peopleJsonObject.put("addr", addr)
        peopleJsonObject.put("age", age)
        return peopleJsonObject
    }

    @Throws(JSONException::class)
    private fun readObject(json: String?): JsonData {
        val jsonObject = JSONObject(json)

        val data = JsonData()
        data.aString = jsonObject.getString("aString")
        data.aBoolean = jsonObject.getBoolean("aBoolean")
        data.aInt = jsonObject.getInt("aInt")
        data.aDouble = jsonObject.getDouble("aDouble")
        data.aObject = getPeople(jsonObject.getJSONObject("aObject"))

        val jsonArray = jsonObject.getJSONArray("aStringArray")
        data.aStringArray = arrayOfNulls(jsonArray.length())
        for (index in 0 until jsonArray.length()) {
            data.aStringArray[index] = jsonArray.getString(index)
        }

        val objectArray = jsonObject.getJSONArray("aObjectArray")
        data.aObjectArray = arrayOfNulls(objectArray.length())
        for (index in 0 until objectArray.length()) {
            data.aObjectArray[index] = getPeople(objectArray.getJSONObject(index))
        }

        return data
    }

    @Throws(JSONException::class)
    private fun getPeople(jsonObject: JSONObject): People {
        val id = jsonObject.getInt("id")
        val name = jsonObject.getString("name")
        val addr = jsonObject.getString("addr")
        val age = jsonObject.getInt("age")
        return People(id, name, addr, age)
    }
}