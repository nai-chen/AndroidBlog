package com.blog.demo.md.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blog.demo.R;

public class RecyclerViewLayoutSpanActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_recycler_view_normal);

        // 设置水平布局方式
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        GridLayoutManager gridSpanLayoutManager = new GridLayoutManager(this, 4);
        gridSpanLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 5 == 0) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridSpanLayoutManager);
        recyclerView.setAdapter(new RecyclerViewGridAdapter(this));
    }

}
