package com.blog.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.blog.demo.R;

public class PersonalInfoDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_personal_info_detail);

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvGender = findViewById(R.id.tv_gender);
        TextView tvFav = findViewById(R.id.tv_fav);

        tvName.setText(getIntent().getStringExtra("name"));
        tvGender.setText(getIntent().getStringExtra("gender"));
        tvFav.setText(getIntent().getStringExtra("favorite"));
    }

}
