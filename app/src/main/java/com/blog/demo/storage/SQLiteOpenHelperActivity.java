package com.blog.demo.storage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.blog.demo.LogUtil;
import com.blog.demo.People;
import com.blog.demo.PeopleSQLiteOpenHelper;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/7.
 */

public class SQLiteOpenHelperActivity extends Activity implements View.OnClickListener {

    private EditText mEtName, mEtAddr, mEtAge;
    private PeopleSQLiteOpenHelper mHelper;

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
                People people = peopleList.get(position);
                SQLiteOpenHelperActivity.this.id = people.getId();
                LogUtil.log("SQLiteOpenHelperActivity", "id = " + id);
                mEtName.setText(people.getName());
                mEtAddr.setText(people.getAddr());
                mEtAge.setText(Integer.toString(people.getAge()));
            }
        });
        mHelper = new PeopleSQLiteOpenHelper(this);

        queryList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mHelper.add(mEtName.getText().toString(), mEtAddr.getText().toString(),
                        Integer.parseInt(mEtAge.getText().toString()));
                queryList();
                break;
            case R.id.btn_query:
                queryList();
                break;
            case R.id.btn_delete:
                LogUtil.log("SQLiteOpenHelperActivity", "delete id = " + id);
                if (id != -1) {
                    mHelper.delete(id);
                    queryList();
                    id = -1;
                }
                break;
            case R.id.btn_modify:
                LogUtil.log("SQLiteOpenHelperActivity", "modify id = " + id);
                if (id != -1) {
                    mHelper.modify(id, mEtName.getText().toString(), mEtAddr.getText().toString(),
                            Integer.parseInt(mEtAge.getText().toString()));
                    queryList();
                    id = -1;
                }
                break;
            case R.id.btn_clear:
                mHelper.clear();
                queryList();
                break;
        }
    }

    private void queryList() {
        mAdapter.clear();
        peopleList = mHelper.query();

        List<String> list = new ArrayList<String>();
        for (People people : peopleList) {
            list.add(people.toString());
        }
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

}
