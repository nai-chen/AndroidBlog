package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.blog.demo.R

class ListViewArrayAdapterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view)
        val listView: ListView = findViewById(R.id.list_view)
        val data = arrayOf("Peter", "Lily", "Jack", "Mike")
        listView.adapter = ArrayAdapter(this, R.layout.list_view_item, R.id.tv_name, data)
    }

}