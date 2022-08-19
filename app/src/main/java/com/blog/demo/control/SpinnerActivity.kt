package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.widget.Spinner
import com.blog.demo.People
import com.blog.demo.R
import com.blog.demo.control.list.CustomAdapter

class SpinnerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_spinner)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = CustomAdapter(this,
                arrayOf(
                    People("Peter", "ShangHai"),
                    People("Lily", "BeiJing"),
                    People("Jack", "GuangZhou"),
                    People("Mike", "ShengZhen")
                ).asList()
        )
    }
}