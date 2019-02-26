package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.blog.demo.R;
import com.blog.demo.custom.widget.CListView;

public class CListViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_list_view);

        final CListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.list_view_item, R.id.tv_name, getResources().getStringArray(R.array.month)));


        listView.setOnRefreshListener(new CListView.IOnRefreshListener() {
            @Override
            public void onRefreshStart() {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.refreshFinish();
                    }
                }, 3000);
            }
        });
    }
}
