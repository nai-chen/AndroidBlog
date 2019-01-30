package com.blog.demo.control.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.R;

public class ListViewScrollBarStyleOutsideActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_list_view_scroll_bar_style_outside);

        String[] data = new String[10];
        for (int index = 0; index < 10; index++) {
            data[index] = "item" + index;
        }
        ListView listView = findViewById(R.id.list_view1);
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.list_view_item, R.id.tv_name, data));

        listView = findViewById(R.id.list_view2);
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.list_view_item, R.id.tv_name, data));
    }

}
