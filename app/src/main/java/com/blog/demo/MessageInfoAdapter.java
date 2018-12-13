package com.blog.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cn on 2017/3/16.
 */

public class MessageInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageInfo> mInfos;

    public MessageInfoAdapter(Context context, List<MessageInfo> infos) {
        this.mContext = context;
        this.mInfos = infos;
    }

    @Override
    public int getCount() {
        return mInfos.size();
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
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_message_info, parent, false);

            holder = new ViewHolder();
            holder.tvKey = (TextView) convertView.findViewById(R.id.tv_key);
            holder.tvVal1 = (TextView) convertView.findViewById(R.id.tv_val1);
            holder.tvVal2 = (TextView) convertView.findViewById(R.id.tv_val2);
            holder.tvVal3 = (TextView) convertView.findViewById(R.id.tv_val3);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MessageInfo info = mInfos.get(position);
        holder.tvKey.setText(info.key);
        if (info.values.length > 0) {
            holder.tvVal1.setText(info.values[0]);
        }
        if (info.values.length > 1) {
            holder.tvVal2.setText(info.values[1]);
            holder.tvVal2.setVisibility(View.VISIBLE);
        } else {
            holder.tvVal2.setVisibility(View.GONE);
        }
        if (info.values.length > 2) {
            holder.tvVal3.setText(info.values[2]);
            holder.tvVal3.setVisibility(View.VISIBLE);
        } else {
            holder.tvVal3.setVisibility(View.GONE);
        }
        return convertView;
    }

    public static class MessageInfo {
        public String key;
        public String[] values;

        public MessageInfo(String key, String value) {
            this(key, new String[]{value});
        }

        public MessageInfo(String key, String val1, String val2) {
            this(key, new String[]{val1, val2});
        }

        public MessageInfo(String key, String val1, String val2, String val3) {
            this(key, new String[]{val1, val2, val3});
        }

        public MessageInfo(String key, String[] values) {
            this.key = key;
            this.values = Arrays.copyOf(values, values.length);
        }
    }

    private static class ViewHolder {
        TextView tvKey;
        TextView tvVal1;
        TextView tvVal2;
        TextView tvVal3;
    }

}
