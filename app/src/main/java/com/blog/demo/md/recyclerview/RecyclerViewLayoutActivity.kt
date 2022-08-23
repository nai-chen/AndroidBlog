package com.blog.demo.md.recyclerview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_layout)

        // 设置水平布局方式
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerViewHorizontalAdapter(this)

        val gridRecyclerView: RecyclerView = findViewById(R.id.recycler_view_grid)
        gridRecyclerView.layoutManager = GridLayoutManager(this, 4)
//        gridRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        gridRecyclerView.adapter = RecyclerViewGridAdapter(this)
    }

    private class RecyclerViewHorizontalAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewHorizontalAdapter.ItemViewHolder>() {
        private var mContext: Context = context
        private val mContent: MutableList<Int> = ArrayList()

        init {
            for (position in 1..4) {
                mContent.add(R.drawable.flow_2)
                mContent.add(R.drawable.flow_3)
                mContent.add(R.drawable.flow_4)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_picture_horizontal, viewGroup, false)
            )
        }

        override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
            viewHolder.imageView.setImageResource(mContent[position])
        }

        override fun getItemCount(): Int {
            return mContent.size
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView

            init {
                imageView = itemView.findViewById(R.id.image_view)
            }
        }
    }

}