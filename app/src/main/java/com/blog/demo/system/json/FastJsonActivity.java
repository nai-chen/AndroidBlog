package com.blog.demo.system.json;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.demo.LogTool;
import com.blog.demo.People;
import com.blog.demo.R;

public class FastJsonActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private String json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system_fast_json);

        findViewById(R.id.btn_write_fast_json).setOnClickListener(this);
        findViewById(R.id.btn_read_fast_json).setOnClickListener(this);
        findViewById(R.id.btn_write_fast_json_bean).setOnClickListener(this);
        findViewById(R.id.btn_read_fast_json_bean).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write_fast_json) {
            json = writeObject();
            textView.setText(json);
        } else if (v.getId() == R.id.btn_read_fast_json) {
            JsonData data = readObject(json);
            LogTool.logi("FastJsonActivity", data.toString());
            textView.setText(data.toString());
        } else if (v.getId() == R.id.btn_write_fast_json_bean) {
            json = writeJavaBeen();
            textView.setText(json);
        } else if (v.getId() == R.id.btn_read_fast_json_bean) {
            JsonData data = readJavaBeen(json);
            LogTool.logi("FastJsonActivity", data.toString());
            textView.setText(data.toString());
        }
    }


    private String writeObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aString", "This is fastJson string");
        jsonObject.put("aBoolean", true);
        jsonObject.put("aInt", 12);
        jsonObject.put("aDouble", 1.23);

        jsonObject.put("aObject", newPeopleObject(1, "Mike", "ShengZhen", 24));

        JSONArray stringJsonArray = new JSONArray();
        stringJsonArray.add("football");
        stringJsonArray.add("basketball");
        stringJsonArray.add("volleyball");
        jsonObject.put("aStringArray", stringJsonArray);

        JSONArray objectJsonArray = new JSONArray();
        objectJsonArray.add(newPeopleObject(2, "Jack", "ShangHai", 26));
        objectJsonArray.add(newPeopleObject(3, "Lily", "BeiJing", 22));
        jsonObject.put("aObjectArray", objectJsonArray);

        String json = jsonObject.toJSONString();
        return json;
    }

    private JSONObject newPeopleObject(int id, String name, String addr, int age) {
        JSONObject peopleJsonObject = new JSONObject();
        peopleJsonObject.put("id", id);
        peopleJsonObject.put("name", name);
        peopleJsonObject.put("addr", addr);
        peopleJsonObject.put("age", age);
        return peopleJsonObject;
    }

    private JsonData readObject(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);

        JsonData data = new JsonData();
        data.aString = jsonObject.getString("aString");
        data.aBoolean = jsonObject.getBooleanValue("aBoolean");
        data.aInt = jsonObject.getIntValue("aInt");
        data.aDouble = jsonObject.getDoubleValue("aDouble");

        data.aObject = getPeople(jsonObject.getJSONObject("aObject"));

        JSONArray jsonArray = jsonObject.getJSONArray("aStringArray");
        data.aStringArray = new String[jsonArray.size()];
        for (int index = 0; index < jsonArray.size(); index++) {
            data.aStringArray[index] = jsonArray.getString(index);
        }

        JSONArray objectArray = jsonObject.getJSONArray("aObjectArray");
        data.aObjectArray = new People[objectArray.size()];
        for (int index = 0; index < objectArray.size(); index++) {
            data.aObjectArray[index] = getPeople(objectArray.getJSONObject(index));
        }

        return data;
    }

    private People getPeople(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        String name = jsonObject.getString("name");
        String addr = jsonObject.getString("addr");
        int age = jsonObject.getIntValue("age");
        return new People(id, name, addr, age);
    }


    private String writeJavaBeen() {
        JsonData data = new JsonData();
        data.aString = "This is gson string";
        data.aBoolean = true;
        data.aInt = 12;
        data.aDouble = 1.23;

        data.aObject = new People(1, "Mike", "ShengZhen", 24);

        data.aStringArray = new String[]{"football", "basketball", "volleyball"};

        data.aObjectArray = new People[]{ new People(2, "Jack", "ShangHai", 26),
                new People(3, "Lily", "BeiJing", 22) };

        String json = JSON.toJSONString(data);

        return json;
    }

    private JsonData readJavaBeen(String json) {
        return JSON.parseObject(json, JsonData.class);
    }

}
