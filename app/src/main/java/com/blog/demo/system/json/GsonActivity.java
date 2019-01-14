package com.blog.demo.system.json;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.People;
import com.blog.demo.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonActivity extends Activity implements View.OnClickListener {

    private String json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system_gson);

        findViewById(R.id.btn_write_gson).setOnClickListener(this);
        findViewById(R.id.btn_read_gson).setOnClickListener(this);
        findViewById(R.id.btn_write_gson_bean).setOnClickListener(this);
        findViewById(R.id.btn_read_gson_bean).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write_gson) {
            json = writeObject();
        } else if (v.getId() == R.id.btn_read_gson) {
            JsonData data = readObject(json);
            LogTool.logi("GsonActivity", data.toString());
        } else if (v.getId() == R.id.btn_write_gson_bean) {
            json = writeJavaBeen();
        } else if (v.getId() == R.id.btn_read_gson_bean) {
            JsonData data = readJavaBeen(json);
            LogTool.logi("GsonActivity", data.toString());
        }
    }

    private String writeObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("aString", "This is gson string");
        jsonObject.addProperty("aBoolean", true);
        jsonObject.addProperty("aInt", 12);
        jsonObject.addProperty("aDouble", 1.23);

        jsonObject.add("aObject", newPeopleObject(1, "Mike", "ShengZhen", 24));

        JsonArray stringJsonArray = new JsonArray();
        stringJsonArray.add("football");
        stringJsonArray.add("basketball");
        stringJsonArray.add("volleyball");
        jsonObject.add("aStringArray", stringJsonArray);

        JsonArray objectJsonArray = new JsonArray();
        objectJsonArray.add(newPeopleObject(2, "Jack", "ShangHai", 26));
        objectJsonArray.add(newPeopleObject(3, "Lily", "BeiJing", 22));
        jsonObject.add("aObjectArray", objectJsonArray);

        String json = new Gson().toJson(jsonObject);

        return json;
    }

    private JsonObject newPeopleObject(int id, String name, String addr, int age) {
        JsonObject peopleJsonObject = new JsonObject();
        peopleJsonObject.addProperty("id", id);
        peopleJsonObject.addProperty("name", name);
        peopleJsonObject.addProperty("addr", addr);
        peopleJsonObject.addProperty("age", age);
        return peopleJsonObject;
    }

    private JsonData readObject(String json) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);

        JsonData data = new JsonData();
        data.aString = jsonObject.get("aString").getAsString();
        data.aBoolean = jsonObject.get("aBoolean").getAsBoolean();
        data.aInt = jsonObject.get("aInt").getAsInt();
        data.aDouble = jsonObject.get("aDouble").getAsDouble();

        data.aObject = getPeople(jsonObject.get("aObject").getAsJsonObject());

        JsonArray jsonArray = jsonObject.get("aStringArray").getAsJsonArray();
        data.aStringArray = new String[jsonArray.size()];
        for (int index = 0; index < jsonArray.size(); index++) {
            data.aStringArray[index] = jsonArray.get(index).getAsString();
        }

        JsonArray objectArray = jsonObject.get("aObjectArray").getAsJsonArray();
        data.aObjectArray = new People[objectArray.size()];
        for (int index = 0; index < objectArray.size(); index++) {
            data.aObjectArray[index] = getPeople(objectArray.get(index).getAsJsonObject());
        }

        return data;
    }

    private People getPeople(JsonObject jsonObject) {
        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String addr = jsonObject.get("addr").getAsString();
        int age = jsonObject.get("age").getAsInt();
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

        String json = new Gson()
                .toJson(data);

        return json;
    }

    private JsonData readJavaBeen(String json) {
        return new Gson().fromJson(json, JsonData.class);
    }

}
