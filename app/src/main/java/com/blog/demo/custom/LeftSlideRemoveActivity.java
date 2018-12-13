package com.blog.demo.custom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blog.demo.R;
import com.blog.demo.custom.control.LeftSlideRemoveListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cn on 2017/3/31.
 */

public class LeftSlideRemoveActivity extends Activity {
    private List<Map<String, String>> mContentList = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_left_slide_remove);

        Map<String, String> item = new HashMap<String, String>();
        item.put("name", "Peter");
        item.put("address", "ShangHai");
        mContentList.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Lily");
        item.put("address", "BeiJing");
        mContentList.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Jack");
        item.put("address", "GuangZhou");
        mContentList.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Mike");
        item.put("address", "ShengZhen");
        mContentList.add(item);

        LeftSlideRemoveListView lv = (LeftSlideRemoveListView) findViewById(R.id.id_listview);
        lv.setAdapter(new ContractAdapter(this));
        lv.setOnItemRemoveListener(new LeftSlideRemoveListView.OnItemRemoveListener() {
            @Override
            public void onItemRemove(int position) {
                mContentList.remove(position);
            }
        });
    }

    private class ContractAdapter extends LeftSlideRemoveListView.LeftSlideRemoveAdapter {

        public ContractAdapter(Context context) {
            super(context);
        }

        @Override
        public int getCount() {
            return mContentList.size();
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
        public View getSubView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listview_item_customdapter, parent, false);
                holder = new ViewHolder();
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Map<String, String> itemData = mContentList.get(position);
            holder.tvName.setText(itemData.get("name"));
            holder.tvAddress.setText(itemData.get("address"));

            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvName;
        TextView tvAddress;
    }

}
