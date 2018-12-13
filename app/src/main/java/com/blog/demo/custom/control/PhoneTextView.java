package com.blog.demo.custom.control;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.blog.demo.LogUtil;

/**
 * Created by cn on 2017/2/3.
 */

public class PhoneTextView extends EditText {
    private static final String LOGTAG          = "PhoneTextView";

    private static final char SPACE_CHAR        = ' ';
    private static final String SPACE_STRING    = " ";
    private static final int[] EMPTY_PLACE      = new int[]{3, 7};

    private TextWatcher mTextWatcher = new CustomTextWatcher();

    public PhoneTextView(Context context) {
        super(context);
        init();
    }

    public PhoneTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addTextChangedListener(mTextWatcher);
        setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void setPhoneText(String s, int selection) {
        String newStr = "";
        char[] charArray = s.toString().toCharArray();
        int emptyNum = 0;
        for (int index = 0; index < charArray.length; index++) {
            if (isEmptyPlace(index)) {
                newStr += SPACE_CHAR;
                if (index < selection) {
                    ++emptyNum;
                }
            }
            newStr += charArray[index];
        }

        int newSelection = selection + emptyNum;
        removeTextChangedListener(mTextWatcher);
        setText(newStr);
        setSelection(newSelection);
        LogUtil.log(LOGTAG, "Selection = " + newSelection );
        addTextChangedListener(mTextWatcher);
    }

    private boolean isEmptyPlace(int index) {
        for (int i : EMPTY_PLACE) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    private class CustomTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int selection = start + count;
            int emptyNum = 0;
            LogUtil.log(LOGTAG, "s = " + s.toString() + " selection = " + selection);
            for (int index = 0; index < selection; index++) {
                if (s.charAt(index) == SPACE_CHAR) {
                    ++emptyNum;
                }
            }
            selection = selection - emptyNum;

            LogUtil.log(LOGTAG, "start = " + start + "\nbefore = " + before + "\ncount = " + count);
            String newString = s.toString().replace(SPACE_STRING, "");

            LogUtil.log(LOGTAG, "newString = " + newString + " selection = " + selection);
            setPhoneText(newString, selection);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
