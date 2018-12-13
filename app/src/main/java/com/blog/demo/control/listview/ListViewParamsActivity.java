package com.blog.demo.control.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cn on 2017/2/4.
 */

public class ListViewParamsActivity extends Activity {

    private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_params);
        List<String> data = new ArrayList<String>();
        data.add("Peter");
        data.add("Lily");
        data.add("Jack");
        data.add("Mike");

        ListView lv = (ListView) findViewById(R.id.lv1);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item_arrayadapter1, data));

        lv = (ListView) findViewById(R.id.lv2);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item_arrayadapter1, data));
    }

}
