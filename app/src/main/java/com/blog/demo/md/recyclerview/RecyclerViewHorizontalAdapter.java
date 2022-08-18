package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public final class RecyclerViewHorizontalAdapter extends RecyclerView.Adapter<RecyclerViewHorizontalAdapter.ItemViewHolder> {
    private Context mContext;
    private List<Integer> mContent = new ArrayList<>();

    public RecyclerViewHorizontalAdapter(Context context) {
        this.mContext = context;

        for (int position = 1; position <= 4; position++) {
            mContent.add(R.drawable.flow_2);
            mContent.add(R.drawable.flow_3);
            mContent.add(R.drawable.flow_4);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_picture_horizontal, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

}
