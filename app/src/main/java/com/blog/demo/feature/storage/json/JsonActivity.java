package com.blog.demo.feature.storage.json;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.People;
import com.blog.demo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends Activity implements View.OnClickListener {

    private String json;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feature_storage_json);

        findViewById(R.id.btn_write_json).setOnClickListener(this);
        findViewById(R.id.btn_read_json).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write_json) {
            try {
                json = writeObject();
                textView.setText(json);
            } catch (JSONException e) {
            }
        } else if (v.getId() == R.id.btn_read_json) {
            try {
                JsonData data = readObject(json);
                LogTool.logi("JsonActivity", data.toString());
                textView.setText(data.toString());
            } catch (JSONException e) {

            }
        }
    }

    private String writeObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aString", "This is json string");
        jsonObject.put("aBoolean", true);
        jsonObject.put("aInt", 12);
        jsonObject.put("aDouble", 1.23);

        jsonObject.put("aObject", newPeopleObject(1, "Mike", "ShengZhen", 24));

        JSONArray stringJsonArray = new JSONArray();
        stringJsonArray.put("football");
        stringJsonArray.put("basketball");
        stringJsonArray.put("volleyball");
        jsonObject.put("aStringArray", stringJsonArray);

        JSONArray objectJsonArray = new JSONArray();
        objectJsonArray.put(newPeopleObject(2, "Jack", "ShangHai", 26));
        objectJsonArray.put(newPeopleObject(3, "Lily", "BeiJing", 22));
        jsonObject.put("aObjectArray", objectJsonArray);

        String json = jsonObject.toString(4);

        return json;
    }

    private JSONObject newPeopleObject(int id, String name, String addr, int age)
            throws JSONException {
        JSONObject peopleJsonObject = new JSONObject();
        peopleJsonObject.put("id", id);
        peopleJsonObject.put("name", name);
        peopleJsonObject.put("addr", addr);
        peopleJsonObject.put("age", age);
        return peopleJsonObject;
    }

    private JsonData readObject(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        JsonData data = new JsonData();
        data.aString = jsonObject.getString("aString");
        data.aBoolean = jsonObject.getBoolean("aBoolean");
        data.aInt = jsonObject.getInt("aInt");
        data.aDouble = jsonObject.getDouble("aDouble");

        data.aObject = getPeople(jsonObject.getJSONObject("aObject"));

        JSONArray jsonArray = jsonObject.getJSONArray("aStringArray");
        data.aStringArray = new String[jsonArray.length()];
        for (int index = 0; index < jsonArray.length(); index++) {
            data.aStringArray[index] = jsonArray.getString(index);
        }

        JSONArray objectArray = jsonObject.getJSONArray("aObjectArray");
        data.aObjectArray = new People[objectArray.length()];
        for (int index = 0; index < objectArray.length(); index++) {
            data.aObjectArray[index] = getPeople(objectArray.getJSONObject(index));
        }

        return data;
    }

    private People getPeople(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String addr = jsonObject.getString("addr");
        int age = jsonObject.getInt("age");
        return new People(id, name, addr, age);
    }

}
