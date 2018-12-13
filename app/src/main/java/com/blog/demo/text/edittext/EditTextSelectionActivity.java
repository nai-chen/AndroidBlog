package com.blog.demo.text.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/3.
 */

public class EditTextSelectionActivity extends Activity implements View.OnClickListener {

    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edittext_selection);
        mEt = (EditText) findViewById(R.id.et);

        findViewById(R.id.btn_set_selection1).setOnClickListener(this);
        findViewById(R.id.btn_set_selection2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_selection1) {
            mEt.setSelection(5);
        } else if (v.getId() == R.id.btn_set_selection2) {
            mEt.setSelection(3, 6);
        }
    }
}
