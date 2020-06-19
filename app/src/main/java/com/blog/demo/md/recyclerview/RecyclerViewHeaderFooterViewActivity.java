package com.blog.demo.md.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blog.demo.R;

public class RecyclerViewHeaderFooterViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_recycler_view_header_footer_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        adapter.addHeaderView(getLayoutInflater().inflate(R.layout.list_item_header_view1, recyclerView, false));
        adapter.addHeaderView(getLayoutInflater().inflate(R.layout.list_item_header_view2, recyclerView, false));
        adapter.addFooterView(getLayoutInflater().inflate(R.layout.list_item_footer_view, recyclerView, false));
        recyclerView.setAdapter(adapter);
    }

}
