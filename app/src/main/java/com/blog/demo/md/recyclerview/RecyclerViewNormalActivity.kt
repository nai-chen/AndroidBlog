package com.blog.demo.md.recyclerview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewNormalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_normal)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        // 设置布局方式
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerViewNormalAdapter(this)
    }

    private class RecyclerViewNormalAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewNormalAdapter.ItemViewHolder>() {
        private var mContext: Context = context
        private val mContent: MutableList<String> = ArrayList()

        init {
            for (position in 1..40) {
                mContent.add("This is $position item")
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false)
            )
        }

        override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
            viewHolder.textView.text = mContent[position]
        }

        override fun getItemCount(): Int {
            return mContent.size
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView

            init {
                textView = itemView.findViewById(R.id.tv_content)
            }
        }

    }

}