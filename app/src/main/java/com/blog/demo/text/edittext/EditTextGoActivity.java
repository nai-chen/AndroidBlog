package com.blog.demo.text.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/2.
 */

public class EditTextGoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edittext_go);

        EditText etGo = (EditText) findViewById(R.id.et_go);
        etGo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Toast.makeText(EditTextGoActivity.this, "Go Click", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
    }
}
