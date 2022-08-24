package com.blog.demo.feature

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Filter
import android.widget.ListView
import com.blog.demo.R

class FilterActivity : Activity() {
    private lateinit var mEtInput: EditText
    private lateinit var mAdapter: ArrayAdapter<String>
    private val mFilter: Filter = SearchFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_filter)
        mEtInput = findViewById(R.id.et_input)

        val listView: ListView = findViewById(R.id.list_view)
        mAdapter = ArrayAdapter<String>(this, R.layout.list_view_item, R.id.tv_name)
        listView.adapter = mAdapter

        mEtInput.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mFilter.filter(mEtInput.text.toString())
            }

            override fun afterTextChanged(s: Editable) {}

        })
    }

    private inner class SearchFilter : Filter() {
        private val mTextList: MutableList<String> = ArrayList()

        init {
            mTextList.add("abc")
            mTextList.add("abd")
            mTextList.add("abe")
            mTextList.add("acd")
            mTextList.add("acde")
            mTextList.add("ace")
            mTextList.add("bcd")
            mTextList.add("bce")
            mTextList.add("bcf")
        }

        override fun performFiltering(constraint: CharSequence): FilterResults {
            // FilterResults用来保存过滤结果，包含两个属性values和count
            val results = FilterResults()
            val resultList: MutableList<String> = ArrayList()
            for (text in mTextList) {
                if (constraint.isNotEmpty() && text.contains(constraint)) {
                    resultList.add(text)
                }
            }
            results.values = resultList
            results.count = resultList.size

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            mAdapter.clear()
            if (results.count > 0) {
                mAdapter.addAll(results.values as List<String>)
            }
        }

    }

}