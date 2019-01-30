package com.blog.demo.control.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.blog.demo.People;
import com.blog.demo.R;

import java.util.Arrays;

public class ListViewCustomAdapterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_list_view);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new CustomAdapter(this, Arrays.asList(new People[]{
                new People("Peter", "ShangHai"),
                new People("Lily", "BeiJing"),
                new People("Jack", "GuangZhou"),
                new People("Mike", "ShengZhen")})));
    }

}
