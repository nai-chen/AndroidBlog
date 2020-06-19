package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public final class RecyclerViewNormalAdapter extends RecyclerView.Adapter<RecyclerViewNormalAdapter.ItemViewHolder> {
    private Context mContext;
    private List<String> mContent = new ArrayList<>();

    public RecyclerViewNormalAdapter(Context context) {
        this.mContext = context;

        for (int position = 1; position <= 40; position++) {
            mContent.add("This is " + position + " item");
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        viewHolder.textView.setText(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }

}
