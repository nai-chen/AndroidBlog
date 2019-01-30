package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Spinner;

import com.blog.demo.People;
import com.blog.demo.R;
import com.blog.demo.control.list.CustomAdapter;

import java.util.Arrays;

public class SpinnerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_spinner);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new CustomAdapter(this, Arrays.asList(new People[]{
                new People("Peter", "ShangHai"),
                new People("Lily", "BeiJing"),
                new People("Jack", "GuangZhou"),
                new People("Mike", "ShengZhen")})) );
    }

}
