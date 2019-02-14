package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends Activity {

    private EditText mEtInput;
    private ArrayAdapter mAdapter;
    private Filter mFilter = new SearchFilter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_filter);

        mEtInput = findViewById(R.id.et_input);
        ListView listView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, R.id.tv_name);
        listView.setAdapter(mAdapter);

        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFilter.filter(mEtInput.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class SearchFilter extends Filter {
        private List<String> mTextList = new ArrayList<>();

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
            // FilterResults用来保存过滤结果，包含两个属性values和count
            FilterResults results = new FilterResults();

            List<String> resultList = new ArrayList<>();
            for (String text : mTextList) {
                if (constraint != null && constraint.length() > 0 && text.contains(constraint)) {
                    resultList.add(text);
                }
            }

            results.values = resultList;
            results.count = resultList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAdapter.clear();
            if (results.count > 0) {
                mAdapter.addAll((List<String>)results.values);
            }
        }
    }

}
