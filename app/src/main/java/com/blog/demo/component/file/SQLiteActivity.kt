package com.blog.demo.component.file

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.blog.demo.People
import com.blog.demo.R

class SQLiteActivity : Activity(), View.OnClickListener {
    private lateinit var mEtName: EditText
    private lateinit var mEtAddr: EditText
    private lateinit var mEtAge :EditText

    private lateinit var mListView: ListView
    private lateinit var mAdapter: ArrayAdapter<String>
    private var peopleList: List<People> = ArrayList()
    private var mId = -1

    private lateinit var mHelper: PeopleSQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_file_sqlite)
        mEtName = findViewById(R.id.et_name)
        mEtAddr = findViewById(R.id.et_addr)
        mEtAge = findViewById(R.id.et_age)

        findViewById<Button>(R.id.btn_insert).setOnClickListener(this)
        findViewById<Button>(R.id.btn_query).setOnClickListener(this)
        findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
        findViewById<Button>(R.id.btn_update).setOnClickListener(this)

        mListView = findViewById(R.id.list_view)
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        mListView.adapter = mAdapter
        mListView.setOnItemClickListener { _, _, position, _ ->
            mId = peopleList[position].id
            val people = mHelper.query(mId)
            if (people != null) {
                mEtName.setText(people.name)
                mEtAddr.setText(people.addr)
                mEtAge.setText(people.age.toString())
            }
        }
        mHelper = PeopleSQLiteOpenHelper(this)
        queryList()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_insert -> {
                mHelper.insert(mEtName.text.toString(), mEtAddr.text.toString(), mEtAge.text.toString().toInt())
                queryList()
            }
            R.id.btn_query -> {
                queryList()
            }
            R.id.btn_delete -> {
                if (mId != -1) {
                    mHelper.delete(mId)
                    queryList()
                    mId = -1
                }
            }
            R.id.btn_update -> {
                if (mId != -1) {
                    mHelper.update(mId, mEtName.text.toString(), mEtAddr.text.toString(), mEtAge.text.toString().toInt())
                    queryList()
                    mId = -1
                }
            }
        }
    }

    private fun queryList() {
        mAdapter.clear()
        peopleList = mHelper.query()

        val list: MutableList<String> = ArrayList()
        for (people in peopleList) {
            list.add(people.description())
        }
        mAdapter.addAll(list)
        mAdapter.notifyDataSetChanged()

        mEtName.setText("")
        mEtAddr.setText("")
        mEtAge.setText("")
    }

}