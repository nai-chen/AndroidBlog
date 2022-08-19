package com.blog.demo.component.provider

import android.app.Activity
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.blog.demo.People
import com.blog.demo.R
import com.blog.demo.feature.storage.PeopleConstant

class ProviderCustomActivity : Activity(), View.OnClickListener {

    private lateinit var mEtName: EditText
    private lateinit var mEtAddr: EditText
    private lateinit var mEtAge: EditText

    private lateinit var mListView: ListView
    private lateinit var mAdapter: ArrayAdapter<String>
    private var peopleList: List<People> = ArrayList()
    private var mId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_provider_custom)
        mEtName = findViewById(R.id.et_name)
        mEtAddr = findViewById(R.id.et_addr)
        mEtAge = findViewById(R.id.et_age)

        findViewById<Button>(R.id.btn_insert).setOnClickListener(this)
        findViewById<Button>(R.id.btn_query).setOnClickListener(this)
        findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
        findViewById<Button>(R.id.btn_update).setOnClickListener(this)
        findViewById<Button>(R.id.btn_clear).setOnClickListener(this)

        mListView = findViewById(R.id.list_view)
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        mListView.adapter = mAdapter
        mListView.setOnItemClickListener {_, _, position, _ ->
            mId = peopleList[position].id
            query(mId)
        }

        queryList()
    }

    private fun query(id: Int) {
        val cursor: Cursor? = contentResolver.query(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id.toLong()),
            null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.NAME))
                val addr = cursor.getString(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.ADDR))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.AGE))

                mEtName.setText(name)
                mEtAddr.setText(addr)
                mEtAge.setText(Integer.toString(age))
            }
            cursor.close()
        }
    }

    private fun insert(name: String, addr: String, age: Int) {
        val insertValues = ContentValues()
        insertValues.put(PeopleConstant.PeopleColumns.NAME, name)
        insertValues.put(PeopleConstant.PeopleColumns.ADDR, addr)
        insertValues.put(PeopleConstant.PeopleColumns.AGE, age)
        contentResolver.insert(PeopleConstant.CONTENT_URI, insertValues)
    }

    private fun update(id: Int, name: String, addr: String, age: Int) {
        val updateValues = ContentValues()
        updateValues.put(PeopleConstant.PeopleColumns.NAME, name)
        updateValues.put(PeopleConstant.PeopleColumns.ADDR, addr)
        updateValues.put(PeopleConstant.PeopleColumns.AGE, age)
        contentResolver.update(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id.toLong()),
            updateValues, null, null)
    }

    private fun delete(id: Int) {
        contentResolver.delete(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id.toLong()),
            null, null)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_insert -> {
                insert(mEtName.text.toString(), mEtAddr.text.toString(), mEtAge.text.toString().toInt())
                queryList()
            }
            R.id.btn_query -> {
                queryList()
            }
            R.id.btn_delete -> {
                if (mId != -1) {
                    delete(mId)
                    queryList()
                    mId = -1
                }
            }
            R.id.btn_update -> {
                if (mId != -1) {
                    update(mId, mEtName.text.toString(), mEtAddr.text.toString(), mEtAge.text.toString().toInt())
                    queryList()
                    mId = -1
                }
            }
            R.id.btn_clear -> {
                contentResolver.delete(PeopleConstant.CONTENT_URI, null, null)
                queryList()
            }
        }
    }

    fun queryList() {
        mAdapter.clear()
        peopleList = query()

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

    private fun query(): List<People> {
        val cursor: Cursor? = contentResolver.query(
            PeopleConstant.CONTENT_URI,
            arrayOf(
                PeopleConstant.PeopleColumns.ID,
                PeopleConstant.PeopleColumns.NAME,
                PeopleConstant.PeopleColumns.ADDR,
                PeopleConstant.PeopleColumns.AGE
            ),
            null,
            null,
            null
        )
        val list: MutableList<People> = ArrayList()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.NAME))
                val addr = cursor.getString(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.ADDR))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(PeopleConstant.PeopleColumns.AGE))
                list.add(People(id, name, addr, age))
            }
            cursor.close()
        }
        return list
    }
}