package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.blog.demo.R
import com.blog.demo.control.widget.CListView

class CListViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_custom_list_view)

        val listView: CListView = findViewById(R.id.list_view)
        listView.adapter = ArrayAdapter(this,
            R.layout.list_view_item, R.id.tv_name, resources.getStringArray(R.array.month))

        listView.setOnRefreshListener(object : CListView.IOnRefreshListener {
            override fun onRefreshStart() {
                listView.postDelayed({
                    listView.refreshFinish()
                }, 3000)
            }
        })
    }

}