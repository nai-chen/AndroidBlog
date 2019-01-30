package com.blog.demo.control.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.R;

public class ListViewHeaderViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_list_view);

        ListView listView = findViewById(R.id.list_view);
        View headerParentView = getLayoutInflater().inflate(
                R.layout.list_view_header_view, listView, false);
        final View headerView = headerParentView.findViewById(R.id.header_view);
        listView.addHeaderView(headerParentView, null, false);

        String[] data = new String[]{"Peter", "Lily", "Jack", "Mike"};
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.list_view_item, R.id.tv_name, data));

        headerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                headerView.setVisibility(View.GONE);
            }
        });
    }
}
