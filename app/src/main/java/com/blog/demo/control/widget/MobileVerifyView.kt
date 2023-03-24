package com.blog.demo.control.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RelativeLayout
import com.blog.demo.R

class MobileVerifyView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr), View.OnClickListener {


    private var mEtInput: EditText

    private var mItemViewList = mutableListOf<MobileVerifyItemView>()
    private var mTextWatcher: MobileTextWatcher? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.view_mobile_verify, this)

        mEtInput = findViewById(R.id.et_input)

        mItemViewList.add(findViewById(R.id.item_view1))
        mItemViewList.add(findViewById(R.id.item_view2))
        mItemViewList.add(findViewById(R.id.item_view3))
        mItemViewList.add(findViewById(R.id.item_view4))

        // 监听EditText的文本变化，修改显示文本和光标位置
        mEtInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setSelection()
                mTextWatcher?.onTextChanged(mEtInput.text.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        setOnClickListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setSelection()
        showSoftKeyBoard()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        for (itemView in mItemViewList) {
            itemView.setFocus(false)
        }
    }

    // 根据输入文本显示，并移动光标位置
    private fun setSelection() {
        var text = mEtInput.text.toString()
        var len = mEtInput.length()

        for (index in mItemViewList.indices) {
            if (index < len) {
                mItemViewList[index].setText(text.substring(index, index+1))
            } else {
                mItemViewList[index].setText("")
            }
            mItemViewList[index].setFocus(false)
        }

        if (len < 4) {
            mItemViewList[len].setFocus(true)
        } else {
            mItemViewList[3].setFocus(true)
        }
    }

    override fun onClick(v: View) {
        showSoftKeyBoard()
    }

    // 显示软键盘
    private fun showSoftKeyBoard() {
        var manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        postDelayed({
            manager.showSoftInput(mEtInput, 0)
        }, 300)
    }

    // 添加文本监听器
    fun setTextWatcher(textWatcher: MobileTextWatcher) {
        this.mTextWatcher = textWatcher
    }

    interface MobileTextWatcher {
        fun onTextChanged(text: String)
    }

}