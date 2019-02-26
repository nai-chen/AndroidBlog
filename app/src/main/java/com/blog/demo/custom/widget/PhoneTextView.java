package com.blog.demo.custom.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.blog.demo.LogTool;

public class PhoneTextView extends EditText {

    public PhoneTextView(Context context) {
        this(context, null);
    }

    public PhoneTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
        setInputType(InputType.TYPE_CLASS_NUMBER);

        addTextChangedListener(new PhoneTextWatcher(this));
    }

    static class PhoneTextWatcher implements TextWatcher {
        private static final char SPACE_CHAR = ' ';

        private EditText mEditText;

        PhoneTextWatcher(EditText editText) {
            this.mEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int selection = start + count;

            char[] array = new char[s.length()];
            int len = 0;
            int space = 0;
            for (int index = 0; index < s.length(); index++) {
                if (s.charAt(index) == SPACE_CHAR) {
                    if (index < selection) {
                        ++space;
                    }
                } else {
                    array[len++] = s.charAt(index);
                }
            }
            setPhoneText(new String(array, 0, len), selection - space);
        }

        private void setPhoneText(String phoneText, int selection) {
            LogTool.logi("PhoneTextView", phoneText + ", selection = " + selection);
            String text = "";
            int space = 0;
            for (int index = 0; index < phoneText.length(); index++) {
                if (isEmptyPlace(text.length())) {
                    text += SPACE_CHAR;
                    if (index < selection) {
                        ++space;
                    }
                }
                text += phoneText.charAt(index);
            }

            mEditText.removeTextChangedListener(this);
            mEditText.setText(text);
            mEditText.setSelection(selection + space);
            mEditText.addTextChangedListener(this);
        }

        private boolean isEmptyPlace(int index) {
            return 3 == index || 8 == index;
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}
