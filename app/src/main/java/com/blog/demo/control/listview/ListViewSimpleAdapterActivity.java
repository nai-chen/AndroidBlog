package com.blog.demo.control.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cn on 2017/2/4.
 */

public class ListViewSimpleAdapterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_simpleadapter);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map<String, String> item = new HashMap<String, String>();
        item.put("name", "Peter");
        item.put("address", "ShangHai");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Lily");
        item.put("address", "BeiJing");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Jack");
        item.put("address", "GuangZhou");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Mike");
        item.put("address", "ShengZhen");
        data.add(item);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new SimpleAdapter(this, data, R.layout.listview_item_simpleadapter,
                new String[]{"name", "address"}, new int[]{R.id.tv_name, R.id.tv_address}));
    }
}
