package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/3/21.
 */

public class PersonalInfoDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_info_detail);

        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvGender = (TextView) findViewById(R.id.tv_gender);
        TextView tvFav = (TextView) findViewById(R.id.tv_fav);

        tvName.setText(getIntent().getStringExtra("name"));
        tvGender.setText(getIntent().getStringExtra("gender"));
        tvFav.setText(getIntent().getStringExtra("favorite"));
    }

}
