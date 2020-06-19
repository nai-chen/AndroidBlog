package com.blog.demo.md.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.blog.demo.R;

public class RecyclerViewLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_recycler_view_layout);

        // 设置水平布局方式
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerViewHorizontalAdapter(this));

        RecyclerView gridRecyclerView = findViewById(R.id.recycler_view_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        gridRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        gridRecyclerView.setAdapter(new RecyclerViewGridAdapter(this));


    }

}
