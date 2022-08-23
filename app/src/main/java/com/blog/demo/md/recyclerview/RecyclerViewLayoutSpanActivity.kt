package com.blog.demo.md.recyclerview

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewLayoutSpanActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_normal)

        // 设置水平布局方式
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val gridSpanLayoutManager = GridLayoutManager(this, 4)
        gridSpanLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 5 == 0) {
                    4
                } else {
                    1
                }
            }
        }
        recyclerView.layoutManager = gridSpanLayoutManager
        recyclerView.adapter = RecyclerViewGridAdapter(this)
    }

}