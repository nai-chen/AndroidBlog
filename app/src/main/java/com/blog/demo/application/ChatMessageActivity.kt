package com.blog.demo.application

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blog.demo.LogTool
import com.blog.demo.R
import com.blog.demo.md.recyclerview.*
import java.util.*

class ChatMessageActivity : Activity() {

    private lateinit var mRecyclerView: CustomRecyclerView
    private lateinit var mRefreshViewCreator: RefreshViewCreator

    private var mFirstMessage: ChatMessageAdapter.ChatMessage? = null
    private var mLastMessage: ChatMessageAdapter.ChatMessage? = null

    private lateinit var mMessageAdapter: ChatMessageAdapter
    private var mMessageList = mutableListOf<ChatMessageAdapter.ChatMessage>()

    private var mAllMessageList = mutableListOf<ChatMessageAdapter.ChatMessage>()
    private var mShowSoftKeyBoard: Boolean = false

    private var mTimer: Timer = Timer(false)
    private var mTimerTask: TimerTask = object : TimerTask() {
        override fun run() {
            requestNewMessage()
        }
    }

    private val mRefreshListener: RefreshViewCreator.IOnRefreshListener =
        object : RefreshViewCreator.IOnRefreshListener {
            override fun onRefresh() {
                requestMessage()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_chat_message)

        mRecyclerView = findViewById(R.id.recycler_view)
        mMessageAdapter = ChatMessageAdapter(this)
        mRecyclerView.adapter = mMessageAdapter

        mRefreshViewCreator = CustomRefreshViewCreator(mRefreshListener)
        mRecyclerView.setRefreshViewCreator(mRefreshViewCreator)
        mMessageAdapter.addHeaderView(mRefreshViewCreator.onCreateRefreshView(this, mRecyclerView)!!)

        generateAllMessageList()

        window.decorView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            // 获取当前屏幕内容的高度
            var screenHeight = window.decorView.height

            // 获取View可见区域的bottom
            var rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)

            if (screenHeight - rect.bottom > 300) {
                if (!mShowSoftKeyBoard) {
                    mShowSoftKeyBoard = true

                    LogTool.logi("ChatMessageActivity", "mRecyclerView.canScrollVertically() = ${mRecyclerView.canScrollVertically(1)}")
                    if (mRecyclerView.canScrollVertically(1)) {
                        mRecyclerView.scrollBy(0, screenHeight - rect.bottom)
                    }
                }
            } else {
                mShowSoftKeyBoard = false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mTimer.scheduleAtFixedRate(mTimerTask, 0, 100000)
    }

    override fun onPause() {
        super.onPause()

        mTimer.cancel()
    }

    private fun requestNewMessage() {
        LogTool.logi("ChatMessageActivity", "requestNewMessage")
        var messageList = mutableListOf<ChatMessageAdapter.ChatMessage>()
        var msgId = mLastMessage?.id
        if (msgId != null) {
            var lastMsgId = msgId + 1
            if (lastMsgId < 100) {
                messageList.addAll(mAllMessageList.subList(lastMsgId, lastMsgId + 1))
            }
        } else {
            messageList.addAll(mAllMessageList.subList(60, 65))
        }
        mRecyclerView.postDelayed({
            addMessageList(messageList)
        }, 2000)
    }

    private fun addMessageList(messageList: MutableList<ChatMessageAdapter.ChatMessage>) {
        if (messageList.isNotEmpty()) {
            var scrollToBottom = !mRecyclerView.canScrollVertically(1)

            mMessageList.addAll(messageList)
            mFirstMessage = mMessageList.first()
            mLastMessage = mMessageList.last()

            mMessageAdapter.setContent(mMessageList)

            if (scrollToBottom) {
                mRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount - 1)
            }
        }
    }

    private fun requestMessage() {
        LogTool.logi("ChatMessageActivity", "requestMessage")
        var messageList = mutableListOf<ChatMessageAdapter.ChatMessage>()
        var msgId = mFirstMessage?.id
        if (msgId != null) {
            if (msgId != 0) {
                messageList.addAll(mAllMessageList.subList(msgId - 5, msgId))
            }
        } else {
            messageList.addAll(mAllMessageList.subList(60, 69))
        }
        mRecyclerView.postDelayed({
            insertMessageList(messageList)
        }, 2000)
    }

    private fun insertMessageList(messageList: MutableList<ChatMessageAdapter.ChatMessage>) {
        if (messageList.isNotEmpty()) {
            var layoutManager = mRecyclerView.layoutManager as LinearLayoutManager
            var itemHeight = layoutManager.findViewByPosition(1)?.height ?: 0

            mMessageList.addAll(0, messageList)
            mFirstMessage = mMessageList.first()
            mLastMessage = mMessageList.last()

            mMessageAdapter.setContent(mMessageList)

            var totalHeight = 0
            for (index in 0 until messageList.size + 1) {
                totalHeight += getItemHeight(index)
            }
            mRecyclerView.scrollBy(0, totalHeight - itemHeight)
        }
        mRefreshViewCreator.refreshFinish()
    }

    private fun getItemHeight(position: Int): Int {
        var viewHolder = mMessageAdapter.onCreateViewHolder(mRecyclerView,
            mMessageAdapter.getItemViewType(mMessageAdapter.getHeaderViewSize() + position))
        viewHolder.bindViewHolder(position)
        viewHolder.itemView.measure(
            View.MeasureSpec.makeMeasureSpec(mRecyclerView.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        LogTool.logi("UserMessageChatActivity", "position = $position, height = ${viewHolder.itemView.measuredHeight}")
        return viewHolder.itemView.measuredHeight
    }

    private fun generateAllMessageList() {
        var messageArray = mutableListOf("你有一条新消息", "hello world！", "白日依山尽，黄河入海流。",
            getString(R.string.text_ellipse), getString(R.string.list_view_refreshing), getString(R.string.poetry))

        for (index in 0 until 100) {
            var chatMessage = ChatMessageAdapter.ChatMessage()
            chatMessage.id = index
            chatMessage.msg_content = messageArray[Random().nextInt(6)]
            chatMessage.isMine = Random().nextInt(10)  % 2 == 1
            mAllMessageList.add(chatMessage)
        }
    }

}