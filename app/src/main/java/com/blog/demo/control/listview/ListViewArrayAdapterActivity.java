package com.blog.demo.control.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/4.
 */

public class ListViewArrayAdapterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_arrayadapter);

        List<String> data = new ArrayList<String>();
        data.add("Peter");
        data.add("Lily");
        data.add("Jack");
        data.add("Mike");

        ListView lv = (ListView) findViewById(R.id.lv1);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item_arrayadapter1, data));

        lv = (ListView) findViewById(R.id.lv2);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item_arrayadapter2, R.id.tv_name, data));
    }
}
