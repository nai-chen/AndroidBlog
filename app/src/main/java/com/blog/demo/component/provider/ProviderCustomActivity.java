package com.blog.demo.component.provider;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.blog.demo.People;
import com.blog.demo.R;
import com.blog.demo.feature.storage.PeopleConstant;
import com.blog.demo.feature.storage.PeopleConstant.PeopleColumns;

import java.util.ArrayList;
import java.util.List;

public class ProviderCustomActivity extends Activity implements View.OnClickListener {

    private EditText mEtName, mEtAddr, mEtAge;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<People> peopleList = new ArrayList<>();
    private int mId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_provider_custom);

        mEtName = findViewById(R.id.et_name);
        mEtAddr = findViewById(R.id.et_addr);
        mEtAge = findViewById(R.id.et_age);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);

        mListView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mId = peopleList.get(position).id;
                query(mId);
            }
        });

        queryList();
    }

    private void query(int id) {
        Cursor cursor = getContentResolver().query(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id),
                null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(PeopleColumns.NAME));
                String addr = cursor.getString(cursor.getColumnIndex(PeopleColumns.ADDR));
                int age = cursor.getInt(cursor.getColumnIndex(PeopleColumns.AGE));
                mEtName.setText(name);
                mEtAddr.setText(addr);
                mEtAge.setText(Integer.toString(age));
            }
            cursor.close();
        }
    }

    private void insert(String name, String addr, int age) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(PeopleColumns.NAME, name);
        insertValues.put(PeopleColumns.ADDR, addr);
        insertValues.put(PeopleColumns.AGE, age);

        getContentResolver().insert(PeopleConstant.CONTENT_URI, insertValues);
    }

    private void update(int id, String name, String addr, int age) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(PeopleColumns.NAME, name);
        updateValues.put(PeopleColumns.ADDR, addr);
        updateValues.put(PeopleColumns.AGE, age);
        getContentResolver().update(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id),
                updateValues, null, null);
    }

    private void delete(int id) {
        getContentResolver().delete(ContentUris.withAppendedId(PeopleConstant.CONTENT_URI, id),
                null, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                insert(mEtName.getText().toString(),
                        mEtAddr.getText().toString(),
                        Integer.parseInt(mEtAge.getText().toString()));
                queryList();
                break;
            case R.id.btn_query:
                queryList();
                break;
            case R.id.btn_delete:
                if (mId != -1) {
                    delete(mId);
                    queryList();
                    mId = -1;
                }
                break;
            case R.id.btn_update:
                if (mId != -1) {
                    update(mId, mEtName.getText().toString(),
                            mEtAddr.getText().toString(),
                            Integer.parseInt(mEtAge.getText().toString()));
                    queryList();
                    mId = -1;
                }
                break;
            case R.id.btn_clear:
                getContentResolver().delete(PeopleConstant.CONTENT_URI, null, null);
                queryList();
                break;
        }
    }

    private void queryList() {
        mAdapter.clear();
        peopleList = query();

        List<String> list = new ArrayList<>();
        for (People people : peopleList) {
            list.add(people.description());
        }
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();

        mEtName.setText("");
        mEtAddr.setText("");
        mEtAge.setText("");
    }

    private List<People> query() {
        Cursor cursor = getContentResolver().query(PeopleConstant.CONTENT_URI,
                new String[]{PeopleColumns.ID, PeopleColumns.NAME, PeopleColumns.ADDR, PeopleColumns.AGE},
                null, null, null);

        List<People> list = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(PeopleColumns.ID));
                String name = cursor.getString(cursor.getColumnIndex(PeopleColumns.NAME));
                String addr = cursor.getString(cursor.getColumnIndex(PeopleColumns.ADDR));
                int age = cursor.getInt(cursor.getColumnIndex(PeopleColumns.AGE));
                list.add(new People(id, name, addr, age));
            }
            cursor.close();
        }
        return list;
    }

}
