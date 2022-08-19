package com.blog.demo.control.edit

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import android.widget.EditText
import com.blog.demo.R

class EditTextPasswordActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_edit_text_password)

        val etInputType: EditText = findViewById(R.id.edit_text_password_input_type)
        val cbInputType: CheckBox = findViewById(R.id.cb_password_input_type)
        cbInputType.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etInputType.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                etInputType.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        val etTransformationMethod: EditText = findViewById(R.id.edit_text_password_transformation_method)
        val cbTransformationMethod:CheckBox = findViewById(R.id.cb_password_transformation_method)
        cbTransformationMethod.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etTransformationMethod.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                etTransformationMethod.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }

}