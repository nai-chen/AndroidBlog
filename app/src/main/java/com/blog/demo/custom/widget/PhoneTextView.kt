package com.blog.demo.custom.widget

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.blog.demo.LogTool.logi

class PhoneTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        filters = arrayOf<InputFilter>(LengthFilter(13))
        inputType = InputType.TYPE_CLASS_NUMBER

        addTextChangedListener(PhoneTextWatcher(this))
    }

    internal class PhoneTextWatcher(private val mEditText: EditText) : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val selection = start + count
            val array = CharArray(s.length)
            var len = 0
            var space = 0
            for (index in s.indices) {
                if (s[index] == SPACE_CHAR) {
                    if (index < selection) {
                        ++space
                    }
                } else {
                    array[len++] = s[index]
                }
            }
            setPhoneText(String(array, 0, len), selection - space)
        }

        private fun setPhoneText(phoneText: String, selection: Int) {
            logi("PhoneTextView", "$phoneText, selection = $selection")
            var text = ""
            var space = 0
            for (index in phoneText.indices) {
                if (isEmptyPlace(text.length)) {
                    text += SPACE_CHAR
                    if (index < selection) {
                        ++space
                    }
                }
                text += phoneText[index]
            }
            mEditText.removeTextChangedListener(this)
            mEditText.setText(text)
            mEditText.setSelection(selection + space)
            mEditText.addTextChangedListener(this)
        }

        private fun isEmptyPlace(index: Int): Boolean {
            return 3 == index || 8 == index
        }

        override fun afterTextChanged(s: Editable) {}

        companion object {
            private const val SPACE_CHAR = ' '
        }
    }

}