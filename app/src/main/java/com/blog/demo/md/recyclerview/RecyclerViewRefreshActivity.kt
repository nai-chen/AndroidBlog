package com.blog.demo.md.recyclerview

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.blog.demo.R
import com.blog.demo.md.recyclerview.LoadViewCreator.IOnLoadListener
import com.blog.demo.md.recyclerview.RecyclerViewAdapter.IOnItemClickListener

class RecyclerViewRefreshActivity : Activity() {

    private val mHandler = Handler()
    private lateinit var mRefreshViewCreator: RefreshViewCreator
    private lateinit var mLoadViewCreator: LoadViewCreator

    private val mRefreshListener: RefreshViewCreator.IOnRefreshListener =
        object : RefreshViewCreator.IOnRefreshListener {
            override fun onRefresh() {
                mHandler.postDelayed({ mRefreshViewCreator!!.refreshFinish() }, 3000)
            }
        }
    private val mLoadListener: IOnLoadListener = object : IOnLoadListener {
        override fun onLoad() {
            mHandler.postDelayed({ mLoadViewCreator!!.loadFinish() }, 3000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_refresh)

        val recyclerView: CustomRecyclerView = findViewById(R.id.recycler_view)
        val adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter

        mRefreshViewCreator = CustomRefreshViewCreator(mRefreshListener)
        recyclerView.setRefreshViewCreator(mRefreshViewCreator)
        adapter.addHeaderView(mRefreshViewCreator.onCreateRefreshView(this, recyclerView)!!)

        mLoadViewCreator = CustomLoadViewCreator(mLoadListener)
        recyclerView.setLoadViewCreator(mLoadViewCreator)
        adapter.addFooterView(mLoadViewCreator.onCreateLoadView(this, recyclerView)!!)

        adapter.setItemClickListener(object : IOnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@RecyclerViewRefreshActivity,
                    "position $position selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}