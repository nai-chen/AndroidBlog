package com.blog.demo.control.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blog.demo.People;
import com.blog.demo.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    List<People> mData;

    public CustomAdapter(Context context, List<People> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        People people = mData.get(position);
        viewHolder.tvName.setText(people.name);
        viewHolder.tvAddress.setText(people.addr);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvAddress;
    }

}
