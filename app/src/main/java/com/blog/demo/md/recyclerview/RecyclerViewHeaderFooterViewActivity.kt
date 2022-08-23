package com.blog.demo.md.recyclerview

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewHeaderFooterViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_header_footer_view)

        val recyclerView:RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = RecyclerViewAdapter(this)
        adapter.addHeaderView(layoutInflater.inflate(R.layout.list_item_header_view1, recyclerView, false))
        adapter.addHeaderView(layoutInflater.inflate(R.layout.list_item_header_view2, recyclerView, false))
        adapter.addFooterView(layoutInflater.inflate(R.layout.list_item_footer_view, recyclerView, false))
        recyclerView.adapter = adapter
    }

}