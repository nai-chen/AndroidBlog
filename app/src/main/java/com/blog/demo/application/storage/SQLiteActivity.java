package com.blog.demo.application.storage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.blog.demo.People;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends Activity implements View.OnClickListener {
    private EditText mEtName, mEtAddr, mEtAge;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<People> peopleList = new ArrayList<>();
    private int mId = -1;

    private PeopleSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_storage_sqlite);

        mEtName = findViewById(R.id.et_name);
        mEtAddr = findViewById(R.id.et_addr);
        mEtAge = findViewById(R.id.et_age);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);

        mListView = findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mId = peopleList.get(position).id;
                People people = mHelper.query(mId);
                if (people != null) {
                    mEtName.setText(people.name);
                    mEtAddr.setText(people.addr);
                    mEtAge.setText(Integer.toString(people.age));
                }
            }
        });

        mHelper = new PeopleSQLiteOpenHelper(this);
        queryList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                mHelper.insert(mEtName.getText().toString(),
                        mEtAddr.getText().toString(),
                        Integer.parseInt(mEtAge.getText().toString()));
                queryList();
                break;
            case R.id.btn_query:
                queryList();
                break;
            case R.id.btn_delete:
                if (mId != -1) {
                    mHelper.delete(mId);
                    queryList();
                    mId = -1;
                }
                break;
            case R.id.btn_update:
                if (mId != -1) {
                    mHelper.update(mId, mEtName.getText().toString(),
                            mEtAddr.getText().toString(),
                            Integer.parseInt(mEtAge.getText().toString()));
                    queryList();
                    mId = -1;
                }
                break;
        }
    }

    private void queryList() {
        mAdapter.clear();
        peopleList = mHelper.query();

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
}
