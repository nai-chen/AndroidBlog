package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/30.
 */

public class FilterActivity extends Activity {
    private EditText mEtInput;
    private FilterAdapter mAdapter;
    private List<String> mContentList = new ArrayList<String>();
    private Filter mFilter = new SearchFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_filter);
        mEtInput = (EditText) findViewById(R.id.et_input);
        ListView listView = (ListView) findViewById(R.id.id_listview);
        mAdapter = new FilterAdapter();
        listView.setAdapter(mAdapter);

        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                queryText(mEtInput.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void queryText(String searchText) {
        mFilter.filter(searchText);
    }

    private class SearchFilter extends Filter {
        private List<String> mTextList = new ArrayList<String>();

        public SearchFilter() {
            mTextList.add("abc");
            mTextList.add("abd");
            mTextList.add("abe");
            mTextList.add("acd");
            mTextList.add("acde");
            mTextList.add("ace");
            mTextList.add("bcd");
            mTextList.add("bce");
            mTextList.add("bcf");
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            List<String> resultList = new ArrayList<String>();

            for (String text : mTextList) {
                if (text.contains(constraint)) {
                    resultList.add(text);
                }
            }

            results.values = resultList;
            results.count = resultList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<String> resultList = new ArrayList<String>();
            if (results.count == 0) {
                mContentList.clear();
            } else {
                mContentList.clear();
                mContentList.addAll((List<String>)results.values);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private class FilterAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mContentList.size();
        }

        @Override
        public Object getItem(int position) {
            return mContentList.get(position);
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

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(mContentList.get(position));

            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvName;
    }

}
