package com.blog.demo.control.edit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.blog.demo.R;

public class EditTextPasswordActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_edit_text_password);

        final EditText etInputType = findViewById(R.id.edit_text_password_input_type);
        CheckBox cbInputType = findViewById(R.id.cb_password_input_type);
        cbInputType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etInputType.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etInputType.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        final EditText etTransformationMethod = findViewById(R.id.edit_text_password_transformation_method);
        CheckBox cbTransformationMethod = findViewById(R.id.cb_password_transformation_method);
        cbTransformationMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etTransformationMethod.setTransformationMethod(
                            HideReturnsTransformationMethod.getInstance());
                } else {
                    etTransformationMethod.setTransformationMethod(
                            PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

}
