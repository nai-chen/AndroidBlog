package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewGridAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewGridAdapter.ItemViewHolder>() {
    private var mContext: Context = context
    private val mContent: MutableList<Int> = ArrayList()

    init {
        for (position in 1..4) {
            mContent.add(R.drawable.flow_1)
            mContent.add(R.drawable.flow_2)
            mContent.add(R.drawable.flow_3)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.list_item_picture_grid, viewGroup, false)
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