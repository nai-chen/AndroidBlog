package com.blog.demo.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.R

class ChatMessageAdapter(context: Context) : RecyclerView.Adapter<ChatMessageAdapter.ItemViewHolder>() {
    private val VIEW_TYPE_HEADER_VIEW = 100
    private val VIEW_TYPE_CONTENT_VIEW = 200

    private val mContext: Context = context
    private val mContent: MutableList<ChatMessage> = ArrayList()
    private val mHeaderViewList: ArrayList<View> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return if (position < getHeaderViewSize()) {
            VIEW_TYPE_HEADER_VIEW + position
        } else {
            VIEW_TYPE_CONTENT_VIEW
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        return if (viewType == VIEW_TYPE_CONTENT_VIEW) {
            ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_chat_message, viewGroup, false))
        } else {
            HeaderFooterView(mHeaderViewList[viewType - VIEW_TYPE_HEADER_VIEW])
        }
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        if (position >= getHeaderViewSize()) {
            viewHolder.bindViewHolder(position - getHeaderViewSize())
        }
    }

    override fun getItemCount(): Int {
        return getHeaderViewSize() + mContent.size
    }

    fun addHeaderView(headerView: View) {
        this.mHeaderViewList.add(headerView)
    }

    open fun getHeaderViewSize(): Int {
        return mHeaderViewList.size
    }

    fun setContent(contentList: MutableList<ChatMessage>?) {
        mContent.clear()
        if (contentList != null) {
            mContent.addAll(contentList)
        }
        notifyDataSetChanged()
    }

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bindViewHolder(position: Int) {}
    }

    private inner class ContentViewHolder(itemView: View) : ItemViewHolder(itemView) {
        private var ivUser: ImageView = itemView.findViewById(R.id.iv_user)
        private var tvMessageUser: TextView = itemView.findViewById(R.id.tv_message_user)
        private var tvMessageMine: TextView = itemView.findViewById(R.id.tv_message_mine)
        private var ivMine: ImageView = itemView.findViewById(R.id.iv_mine)

        override fun bindViewHolder(position: Int) {
            var message = mContent.getOrNull(position)

            if (message?.isMine == true) {
                ivUser.visibility = View.INVISIBLE
                ivMine.visibility = View.VISIBLE

                tvMessageUser.visibility = View.GONE
                tvMessageMine.visibility = View.VISIBLE
                tvMessageMine.text = message.msg_content
            } else {
                ivUser.visibility = View.VISIBLE
                ivMine.visibility = View.INVISIBLE

                tvMessageUser.visibility = View.VISIBLE
                tvMessageUser.text = message?.msg_content
                tvMessageMine.visibility = View.GONE
            }
        }

    }

    private class HeaderFooterView(itemView: View) : ItemViewHolder(itemView)

    class ChatMessage {
        var id: Int = 0
        var msg_content: String? = null
        var isMine: Boolean = false
    }

}