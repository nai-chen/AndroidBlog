package com.blog.demo.md.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blog.demo.R;

public class RecyclerViewLoadMoreActivity extends Activity {
    private Handler mHandler = new Handler();
    private boolean mLoadMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_recycler_view_load_more);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        View footerView = getLayoutInflater().inflate(R.layout.list_view_load_more_view, recyclerView, false);
        adapter.addFooterView(footerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!mLoadMore && layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    mLoadMore = true;
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addContent();
                            mLoadMore = false;
                        }
                    }, 1000);
                }
            }
        });
    }

}
