package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cn on 2017/10/26.
 */

public class SpinnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_student);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String stud = getResources().getStringArray(R.array.student_list)[position];
                Toast.makeText(SpinnerActivity.this, "select " + stud, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner_student2);
        spinner.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter {
        private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        public CustomAdapter() {
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
        }

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
            LogUtil.log("SpinnerActivity", "getView position = " + position);
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

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            LogUtil.log("SpinnerActivity", "getDropDownView position = " + position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listview_item_customdapter, parent, false);

                holder = new ViewHolder();
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Map<String, String> itemData = data.get(position);
            holder.tvName.setText(itemData.get("name"));

            return convertView;
        }

        private class ViewHolder {
            TextView tvName;
            TextView tvAddress;
        }
    }

}
