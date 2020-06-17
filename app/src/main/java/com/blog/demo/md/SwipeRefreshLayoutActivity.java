package com.blog.demo.md;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_swipe_refresh_layout);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.green, R.color.colorAccent);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.yellow);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setProgressViewOffset(false, 0, 400);
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final StringAdapter adapter = new StringAdapter(this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refresh();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private static class StringAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private List<String> content = new ArrayList<>();

        private StringAdapter(Context context) {
            this.mContext = context;
            for (int index = 1; index <= 20; index++) {
                content.add("item" + index);
            }
        }

        private void refresh() {
            content.add(0,"item0");
            notifyDataSetChanged();

            Toast.makeText(mContext, "刷新成功", Toast.LENGTH_SHORT).show();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false);
            return new StringViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            String text = content.get(position);
            ((StringViewHolder) viewHolder).textView.setText(text);
        }

        @Override
        public int getItemCount() {
            return content.size();
        }

    }

    private static class StringViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_content);
        }

    }

}
