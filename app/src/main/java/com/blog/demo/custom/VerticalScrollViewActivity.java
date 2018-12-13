package com.blog.demo.custom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.blog.demo.R;
import com.blog.demo.custom.control.VerticalScrollView;

/**
 * Created by cn on 2018/1/16.
 */

public class VerticalScrollViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vertical_scroll_view);

        VerticalScrollView scrollView = (VerticalScrollView) findViewById(R.id.vertical_scroll_view);
        scrollView.setAdapter(new VerticalAdapter(this, new int[]{
                R.drawable.frame1,
                R.drawable.frame2,
                R.drawable.frame3,
                R.drawable.frame4
        }));
    }

    static class VerticalAdapter extends BaseAdapter {
        Context mContext;
        int[] mResIdArray;


        public VerticalAdapter(Context context, int[] resIdArray) {
            this.mContext = context;
            this.mResIdArray = resIdArray;
        }

        @Override
        public int getCount() {
            return mResIdArray.length;
        }

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

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.listview_item_image_view, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.ivPic.setImageResource(mResIdArray[position]);

            return convertView;
        }

        class ViewHolder {
            ImageView ivPic;
        }

    }

}
