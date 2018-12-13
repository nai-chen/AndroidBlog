package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/6.
 */

public class ListViewScrollBarActivity extends Activity {

    private String[] values = new String[] {
            "item1", "item2", "item3", "item4",
            "item5", "item6", "item7", "item8",
            "item9", "item10", "item11", "item12"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_scroll_bar);

        ListView lv = (ListView) findViewById(R.id.lv1);
        initListView(lv);

        lv = (ListView) findViewById(R.id.lv2);
        initListView(lv);

        lv = (ListView) findViewById(R.id.lv3);
        initListView(lv);
    }

    private void initListView(ListView lv) {
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));
    }

}
