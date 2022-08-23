package com.blog.demo.md

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blog.demo.R

class SwipeRefreshLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_swipe_refresh_layout)

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.green, R.color.colorAccent)
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.yellow)
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE)
        swipeRefreshLayout.setProgressViewOffset(false, 0, 400)
        swipeRefreshLayout.setProgressViewEndTarget(false, 200)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StringAdapter(this)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.postDelayed({
                adapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }, 1000)
        }

    }

    private inner class StringAdapter(val mContext: Context) : RecyclerView.Adapter<StringViewHolder>() {

        private val content: MutableList<String> = ArrayList()

        init {
            for (index in 1..20) {
                content.add("item$index")
            }
        }

        fun refresh() {
            content.add(0, "item0")
            notifyDataSetChanged()
            Toast.makeText(mContext, "刷新成功", Toast.LENGTH_SHORT).show()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StringViewHolder {
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false)
            return StringViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: StringViewHolder, position: Int) {
            val text = content[position]
            viewHolder.textView.text = text
        }

        override fun getItemCount(): Int {
            return content.size
        }

    }

    private inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.tv_content)
        }
    }

}