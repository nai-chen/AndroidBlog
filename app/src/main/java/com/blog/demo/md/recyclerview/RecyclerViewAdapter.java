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

public final class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    private static final int VIEW_TYPE_HEADER_VIEW      = 100;
    private static final int VIEW_TYPE_CONTENT_VIEW     = 200;
    private static final int VIEW_TYPE_FOOTER_VIEW      = 300;

    private Context mContext;
    private List<String> mContent = new ArrayList<>();
    private List<View> mHeaderViewList = new ArrayList<>();
    private List<View> mFooterViewList = new ArrayList<>();

    private IOnItemClickListener mItemClickListener;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;

        for (int position = 1; position <= 20; position++) {
            mContent.add("This is " + position + " item");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderViewSize()) {
            return VIEW_TYPE_HEADER_VIEW + position;
        } else if (position >= getHeaderViewSize() + mContent.size()) {
            return VIEW_TYPE_FOOTER_VIEW + position - getHeaderViewSize() - mContent.size();
        } else {
            return VIEW_TYPE_CONTENT_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_CONTENT_VIEW) {
            return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false));
        } else if (viewType < VIEW_TYPE_CONTENT_VIEW) {
            return new HeaderFooterView(mHeaderViewList.get(viewType - VIEW_TYPE_HEADER_VIEW));
        } else {
            return new HeaderFooterView(mFooterViewList.get(viewType - VIEW_TYPE_FOOTER_VIEW));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ItemViewHolder viewHolder, int position) {
        if (position >= getHeaderViewSize()) {
            viewHolder.bindViewHolder(position - getHeaderViewSize());
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderViewSize() + mContent.size() + getFooterViewSize();
    }

    public void addHeaderView(View headerView) {
        this.mHeaderViewList.add(headerView);
    }

    public void addFooterView(View footerView) {
        this.mFooterViewList.add(footerView);
    }

    private int getHeaderViewSize() {
        return mHeaderViewList.size();
    }

    private int getFooterViewSize() {
        return mFooterViewList.size();
    }

    public void setItemClickListener(IOnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected void bindViewHolder(int position) {
        }

    }

    private class ContentViewHolder extends ItemViewHolder {
        private TextView textView;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }

        @Override
        protected void bindViewHolder(final int position) {
            textView.setText(mContent.get(position));

            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(position);
                    }
                }
            });
        }

    }

    private class HeaderFooterView extends ItemViewHolder {

        public HeaderFooterView(@NonNull View itemView) {
            super(itemView);
        }

    }

    public interface IOnItemClickListener {
        void onItemClick(int position);
    }

}
