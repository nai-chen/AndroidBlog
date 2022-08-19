package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.blog.demo.R

class ListViewScrollBarStyleInsideActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view_scroll_bar_style_inside)

        val data = arrayOfNulls<String>(10)
        for (index in 0..9) {
            data[index] = "item$index"
        }
        var listView: ListView = findViewById(R.id.list_view1)
        listView.adapter = ArrayAdapter(this, R.layout.list_view_item, R.id.tv_name, data)

        listView = findViewById(R.id.list_view2)
        listView.adapter = ArrayAdapter(this, R.layout.list_view_item, R.id.tv_name, data)
    }

}