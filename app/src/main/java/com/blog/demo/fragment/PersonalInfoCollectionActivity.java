package com.blog.demo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/21.
 */

public class PersonalInfoCollectionActivity extends Activity implements TextWatcher,
        View.OnClickListener {
    private EditText mEtName;
    private RadioGroup mRgGender;
    private CheckBox mCbFavMovie, mCbFavTravel, mCbFavSing;
    private Button mBtnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_info_collection);

        mEtName = (EditText) findViewById(R.id.et_name);
        mRgGender = (RadioGroup) findViewById(R.id.rg_gender);

        mCbFavMovie = (CheckBox) findViewById(R.id.cb_fav_movie);
        mCbFavTravel = (CheckBox) findViewById(R.id.cb_fav_travel);
        mCbFavSing = (CheckBox) findViewById(R.id.cb_fav_sing);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);

        mEtName.addTextChangedListener(this);
        mBtnCommit.setOnClickListener(this);

        checkButtonStatus();
    }

    private void checkButtonStatus() {
        // 姓名为空时提交按钮置灰
        mBtnCommit.setEnabled(mEtName.getText().toString().length() > 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkButtonStatus();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PersonalInfoDetailActivity.class);
        intent.putExtra("name", mEtName.getText().toString());
        intent.putExtra("gender", mRgGender.getCheckedRadioButtonId() == R.id.rb_male ? "男" : "女");

        List<String> favList = new ArrayList<String>();
        if (mCbFavMovie.isChecked()) favList.add("电影");
        if (mCbFavTravel.isChecked()) favList.add("旅游");
        if (mCbFavSing.isChecked()) favList.add("唱歌");

        if (favList.size() == 0) {
            intent.putExtra("favorite", "暂无爱好");
        } else {
            intent.putExtra("favorite", join(favList, ","));
        }
        startActivity(intent);
    }

    public String join(List<String> strList, String deli) {
        String joinStr = "";
        for (int index = 0; index < strList.size(); index++) {
            if (index != 0) joinStr += deli;
            joinStr += strList.get(index);
        }
        return joinStr;
    }

}