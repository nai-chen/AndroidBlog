package com.blog.demo.component;

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

import com.blog.demo.Content;
import com.blog.demo.Content.People.PeopleColumns;
import com.blog.demo.People;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/10.
 */

public class ContentProviderCustomActivity extends Activity implements View.OnClickListener {

    private EditText mEtName, mEtAddr, mEtAge;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<People> peopleList = new ArrayList<People>();
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sqlite_open_helper);

        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAddr = (EditText) findViewById(R.id.et_addr);
        mEtAge = (EditText) findViewById(R.id.et_age);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.lv);
        mAdapter = new ArrayAdapter<String>(this, R.layout.listview_item_arrayadapter1);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentProviderCustomActivity.this.id = peopleList.get(position).getId();

                Cursor cursor = getContentResolver().query(ContentUris.withAppendedId(Content.People.CONTENT_URI, ContentProviderCustomActivity.this.id),
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
        });

        queryList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                ContentValues insertValues = new ContentValues();
                insertValues.put(PeopleColumns.NAME, mEtName.getText().toString());
                insertValues.put(PeopleColumns.ADDR, mEtAddr.getText().toString());
                insertValues.put(PeopleColumns.AGE, mEtAge.getText().toString());

                getContentResolver().insert(Content.People.CONTENT_URI, insertValues);
                queryList();
                break;
            case R.id.btn_query:
                queryList();
                break;
            case R.id.btn_delete:
                if (id != -1) {
                    getContentResolver().delete(Content.People.CONTENT_URI,
                            PeopleColumns.ID + "=?", new String[]{Integer.toString(id)});
                    queryList();
                    id = -1;
                }
                break;
            case R.id.btn_modify:
                if (id != -1) {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put(PeopleColumns.NAME, mEtName.getText().toString());
                    updateValues.put(PeopleColumns.ADDR, mEtAddr.getText().toString());
                    updateValues.put(PeopleColumns.AGE, mEtAge.getText().toString());
                    getContentResolver().update(Content.People.CONTENT_URI, updateValues,
                            PeopleColumns.ID + "=?", new String[]{Integer.toString(id)});
                    queryList();
                    id = -1;
                }
                break;
            case R.id.btn_clear:
                getContentResolver().delete(Content.People.CONTENT_URI, null, null);
                queryList();
                break;
        }
    }

    private void queryList() {
        mAdapter.clear();
        peopleList = query();

        List<String> list = new ArrayList<String>();
        for (People people : peopleList) {
            list.add(people.toString());
        }
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    private List<People> query() {
        Cursor cursor = getContentResolver().query(Content.People.CONTENT_URI,
                new String[]{PeopleColumns.ID, PeopleColumns.NAME, PeopleColumns.ADDR, PeopleColumns.AGE},
                null, null, null);

        List<People> list = new ArrayList<People>();
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
