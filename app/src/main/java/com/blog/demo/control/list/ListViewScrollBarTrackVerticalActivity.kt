package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.blog.demo.R

class ListViewScrollBarTrackVerticalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view_scroll_bar_track_vertical)
        val listView: ListView = findViewById(R.id.list_view)
        val data = arrayOfNulls<String>(10)
        for (index in 0..9) {
            data[index] = "item$index"
        }
        listView.adapter = ArrayAdapter(this, R.layout.list_view_item, R.id.tv_name, data)
    }

}