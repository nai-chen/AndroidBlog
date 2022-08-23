package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class RecyclerViewAdapter(context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    private val VIEW_TYPE_HEADER_VIEW = 100
    private val VIEW_TYPE_CONTENT_VIEW = 200
    private val VIEW_TYPE_FOOTER_VIEW = 300

    private val mContext: Context = context
    private val mContent: MutableList<String> = ArrayList()
    private val mHeaderViewList: ArrayList<View> = ArrayList()
    private val mFooterViewList: ArrayList<View> = ArrayList()

    private var mItemClickListener: IOnItemClickListener? = null

    init {
        for (position in 1..20) {
            mContent.add("This is $position item")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < getHeaderViewSize()) {
            VIEW_TYPE_HEADER_VIEW + position
        } else if (position >= getHeaderViewSize() + mContent.size) {
            VIEW_TYPE_FOOTER_VIEW + position - getHeaderViewSize() - mContent.size
        } else {
            VIEW_TYPE_CONTENT_VIEW
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        return if (viewType == VIEW_TYPE_CONTENT_VIEW) {
            ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_text, viewGroup, false))
        } else if (viewType < VIEW_TYPE_CONTENT_VIEW) {
            HeaderFooterView(mHeaderViewList[viewType - VIEW_TYPE_HEADER_VIEW])
        } else {
            HeaderFooterView(mFooterViewList[viewType - VIEW_TYPE_FOOTER_VIEW])
        }
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        if (position >= getHeaderViewSize()) {
            viewHolder.bindViewHolder(position - getHeaderViewSize())
        }
    }

    override fun getItemCount(): Int {
        return getHeaderViewSize() + mContent.size + getFooterViewSize()
    }

    fun addHeaderView(headerView: View) {
        this.mHeaderViewList.add(headerView)
    }

    fun addFooterView(footerView: View) {
        this.mFooterViewList.add(footerView)
    }

    open fun getHeaderViewSize(): Int {
        return mHeaderViewList.size
    }

    open fun getFooterViewSize(): Int {
        return mFooterViewList.size
    }

    fun addContent() {
        val size = mContent.size
        for (position in 1..20) {
            mContent.add("This is " + (size + position) + " item")
        }
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: IOnItemClickListener) {
        this.mItemClickListener = listener
    }

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bindViewHolder(position: Int) {}
    }

    private inner class ContentViewHolder(itemView: View) : ItemViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_content)

        override fun bindViewHolder(position: Int) {
            textView.text = mContent[position]
            textView.setOnClickListener {
                mItemClickListener?.onItemClick(position)
            }
        }

    }

    private class HeaderFooterView(itemView: View) : ItemViewHolder(itemView)

    interface IOnItemClickListener {
        fun onItemClick(position: Int)
    }

}