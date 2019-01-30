package com.blog.demo.control.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewSimpleAdapterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_list_view);

        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("name", "Peter");
        item.put("address", "ShangHai");
        data.add(item);

        item = new HashMap<>();
        item.put("name", "Lily");
        item.put("address", "BeiJing");
        data.add(item);

        item = new HashMap<>();
        item.put("name", "Jack");
        item.put("address", "GuangZhou");
        data.add(item);

        item = new HashMap<>();
        item.put("name", "Mike");
        item.put("address", "ShengZhen");
        data.add(item);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new SimpleAdapter(this, data, R.layout.list_view_item,
            new String[]{"name", "address"}, new int[]{R.id.tv_name, R.id.tv_address}));
    }

}
