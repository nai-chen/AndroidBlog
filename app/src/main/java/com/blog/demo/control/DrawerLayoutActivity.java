package com.blog.demo.control;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2018/4/2.
 */

public class DrawerLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_layout);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                LogUtil.log("DrawerLayout", "onDrawerSlide slideOffset = " + slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                LogUtil.log("DrawerLayout", "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                LogUtil.log("DrawerLayout", "onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                LogUtil.log("DrawerLayout", "onDrawerStateChanged newState = " + newState);
            }
        });

        findViewById(R.id.btn_open_right_pane).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        findViewById(R.id.btn_close_right_pane).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        findViewById(R.id.btn_open_left_pane).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        findViewById(R.id.btn_close_left_pane).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        CheckBox cb = (CheckBox) findViewById(R.id.cb_lock_mode);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                } else {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
    }
}
