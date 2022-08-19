package com.blog.demo.control.list

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.blog.demo.People
import com.blog.demo.R

class ListViewCustomAdapterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_list_view)
        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = CustomAdapter(this,
                arrayOf(
                    People("Peter", "ShangHai"),
                    People("Lily", "BeiJing"),
                    People("Jack", "GuangZhou"),
                    People("Mike", "ShengZhen")
                ).asList()
            )
    }

}