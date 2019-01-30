package com.blog.demo.control.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.R;

public class ListViewArrayAdapterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_list_view);

        ListView listView = findViewById(R.id.list_view);
        String[] data = new String[]{"Peter", "Lily", "Jack", "Mike"};
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.list_view_item, R.id.tv_name, data));
    }
}
