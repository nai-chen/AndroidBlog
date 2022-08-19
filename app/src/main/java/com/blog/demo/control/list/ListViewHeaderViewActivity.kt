package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.blog.demo.R

class ListViewHeaderViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view)

        val listView: ListView = findViewById(R.id.list_view)
        val headerParentView: View = layoutInflater.inflate(R.layout.list_view_header_view, listView, false)
        val headerView: View = headerParentView.findViewById(R.id.header_view)
        listView.addHeaderView(headerParentView, null, false)

        val data = arrayOf("Peter", "Lily", "Jack", "Mike")
        listView.adapter = ArrayAdapter(this, R.layout.list_view_item, R.id.tv_name, data)

        headerView.setOnClickListener {
            headerView.visibility = View.GONE
        }
    }

}