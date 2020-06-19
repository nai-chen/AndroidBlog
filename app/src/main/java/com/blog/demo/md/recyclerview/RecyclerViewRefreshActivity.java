package com.blog.demo.md.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.blog.demo.R;

public class RecyclerViewRefreshActivity extends Activity {
    private Handler mHandler = new Handler();
    private RefreshViewCreator mRefreshViewCreator;
    private LoadViewCreator mLoadViewCreator;

    private RefreshViewCreator.IOnRefreshListener mRefreshListener = new RefreshViewCreator.IOnRefreshListener() {
        @Override
        public void onRefresh() {

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefreshViewCreator.refreshFinish();
                }
            }, 3000);
        }
    };
    private LoadViewCreator.IOnLoadListener mLoadListener = new LoadViewCreator.IOnLoadListener() {
        @Override
        public void onLoad() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLoadViewCreator.loadFinish();
                }
            }, 3000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_recycler_view_refresh);

        CustomRecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        mRefreshViewCreator = new CustomRefreshViewCreator(mRefreshListener);
        recyclerView.setRefreshViewCreator(mRefreshViewCreator);

        adapter.addHeaderView(mRefreshViewCreator.onCreateRefreshView(this, recyclerView));

        mLoadViewCreator = new CustomLoadViewCreator(mLoadListener);
        recyclerView.setLoadViewCreator(mLoadViewCreator);
        adapter.addFooterView(mLoadViewCreator.onCreateLoadView(this, recyclerView));

        adapter.setItemClickListener(new RecyclerViewAdapter.IOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerViewRefreshActivity.this, "position " + position + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
