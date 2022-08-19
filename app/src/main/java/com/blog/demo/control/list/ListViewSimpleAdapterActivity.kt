package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import com.blog.demo.R

class ListViewSimpleAdapterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view)

        val data: MutableList<Map<String, String>> = mutableListOf()

        var item: MutableMap<String, String> = mutableMapOf()
        item["name"] = "Peter"
        item["address"] = "ShangHai"
        data.add(item)

        item = HashMap()
        item["name"] = "Lily"
        item["address"] = "BeiJing"
        data.add(item)

        item = HashMap()
        item["name"] = "Jack"
        item["address"] = "GuangZhou"
        data.add(item)

        item = HashMap()
        item["name"] = "Mike"
        item["address"] = "ShengZhen"
        data.add(item)

        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = SimpleAdapter(this, data, R.layout.list_view_item, arrayOf("name", "address"), intArrayOf(R.id.tv_name, R.id.tv_address))
    }

}