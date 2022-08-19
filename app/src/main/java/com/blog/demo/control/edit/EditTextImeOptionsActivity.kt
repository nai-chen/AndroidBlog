package com.blog.demo.control.edit

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.blog.demo.R

class EditTextImeOptionsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_edit_text_ime_options)

        val editText: EditText = findViewById(R.id.edit_text)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                Toast.makeText(this@EditTextImeOptionsActivity, "Next Click", Toast.LENGTH_LONG).show()
                true
            }
            false
        }
    }

}