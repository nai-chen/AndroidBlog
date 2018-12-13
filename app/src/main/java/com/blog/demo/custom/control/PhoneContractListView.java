package com.blog.demo.custom.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/4/10.
 */

public class PhoneContractListView extends RelativeLayout {
    private View mViewTop;
    private ListView mListView;
    private PhoneContractAdapter mAdapter;
    private int mCurrentPosition = -1;

    public PhoneContractListView(Context context) {
        this(context, null);
    }

    public PhoneContractListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhoneContractListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.listview_phone_contract, this);

        mListView = (ListView) findViewById(R.id.listview);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                setTopText(firstVisibleItem);
            }
        });
    }

    public void setAdapter(PhoneContractAdapter adapter) {
        mAdapter = adapter;
        mListView.setAdapter(mAdapter);

        mAdapter.getCount();
        setTopText(0);
    }

    private void setTopText(int position) {
        if (mAdapter == null || mAdapter.mItemList.size() == 0) return;

        int gPosition = mAdapter.mItemList.get(position).gPosition;

        if (mCurrentPosition != gPosition) {
            mCurrentPosition = gPosition;
            if (mViewTop != null) {
                removeView(mViewTop);
            }
            mViewTop = mAdapter.getGroupTitleView(gPosition, mViewTop, this);
            addView(mViewTop);
        }
    }

    public static abstract class PhoneContractAdapter extends BaseAdapter {
        private Context mContext;

        private List<AdapterItem> mItemList = new ArrayList<AdapterItem>();

        public PhoneContractAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            if (mItemList.size() == 0) {
                for (int gPosition = 0; gPosition < getGroupCount(); gPosition++) {
                    for (int position = 0; position < getGroupItemCount(gPosition); position++) {
                        mItemList.add(new AdapterItem(gPosition, position));
                    }
                }
            }
            return mItemList.size();
        }

        public abstract int getGroupCount();

        public abstract int getGroupItemCount(int gPosition);

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            AdapterItem item = mItemList.get(position);

            if (convertView == null) {
                viewHolder = new ViewHolder();

                LinearLayout layout = new LinearLayout(mContext);

                layout.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        AbsListView.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.VERTICAL);

                convertView = layout;
                viewHolder.viewParent = layout;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();

                viewHolder.viewParent.removeAllViews();
            }

            if (item.position == 0) {
                View titleView = getGroupTitleView(item.gPosition,
                        viewHolder.viewTopTitle, viewHolder.viewParent);
                viewHolder.viewTopTitle = titleView;
                viewHolder.viewParent.addView(titleView);
            }
            View contentView = getGroupContentView(item.gPosition, item.position,
                    viewHolder.viewContent, viewHolder.viewParent);
            viewHolder.viewContent = contentView;
            viewHolder.viewParent.addView(contentView);

            return convertView;
        }

        public abstract View getGroupTitleView(int gPosition, View convertView, ViewGroup parent);

        public abstract View getGroupContentView(int gPosition, int position, View convertView, ViewGroup parent);

    }

    public static class AdapterItem {
        public int gPosition;
        public int position;

        public AdapterItem (int gp, int p) {
            this.gPosition = gp;
            this.position = p;
        }
    }

    private static class ViewHolder {
        ViewGroup viewParent;
        View viewTopTitle;
        View viewContent;
    }
}
