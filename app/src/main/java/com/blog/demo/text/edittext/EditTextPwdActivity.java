package com.blog.demo.text.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/2.
 */

public class EditTextPwdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edittext_pwd);

        final EditText et1 = (EditText) findViewById(R.id.et1);
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et1.setInputType(InputType.TYPE_CLASS_TEXT  |
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    et1.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        final EditText et2 = (EditText) findViewById(R.id.et2);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
