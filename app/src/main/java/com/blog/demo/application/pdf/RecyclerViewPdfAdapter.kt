package com.blog.demo.application.pdf

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewPdfAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewPdfAdapter.ItemViewHolder>() {
    private var mContext: Context = context
    private val mContent: MutableList<Bitmap> = ArrayList()

    fun setBitmapList(bitmapList: MutableList<Bitmap>) {
        mContent.clear()
        mContent.addAll(bitmapList)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.list_item_pdf_view, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.imageView.setImageBitmap(mContent[position])
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