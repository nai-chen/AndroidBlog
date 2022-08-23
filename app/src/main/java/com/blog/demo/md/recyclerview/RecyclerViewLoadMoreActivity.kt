package com.blog.demo.md.recyclerview

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewLoadMoreActivity : Activity() {

    private val mHandler = Handler()
    private var mLoadMore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_load_more)

        val layoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager

        val adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter

        val footerView: View = layoutInflater.inflate(R.layout.list_view_load_more_view, recyclerView, false)
        adapter.addFooterView(footerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!mLoadMore && layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    mLoadMore = true
                    mHandler.postDelayed({
                        adapter.addContent()
                        mLoadMore = false
                    }, 1000)
                }
            }
        })
    }
}