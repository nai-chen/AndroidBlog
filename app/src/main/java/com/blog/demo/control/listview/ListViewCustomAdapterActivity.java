package com.blog.demo.control.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cn on 2017/2/4.
 */

public class ListViewCustomAdapterActivity extends Activity {

    private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_customadapter);

        Map<String, String> item = new HashMap<String, String>();
        item.put("name", "Peter");
        item.put("address", "ShangHai");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Lily");
        item.put("address", "BeiJing");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Jack");
        item.put("address", "GuangZhou");
        data.add(item);

        item = new HashMap<String, String>();
        item.put("name", "Mike");
        item.put("address", "ShengZhen");
        data.add(item);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
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
            Map<String, String> itemData = data.get(position);
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
