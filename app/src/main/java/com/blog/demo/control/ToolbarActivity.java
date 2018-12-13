package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2018/4/3.
 */

public class ToolbarActivity extends Activity implements View.OnClickListener {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        findViewById(R.id.btn_navigation).setOnClickListener(this);
        findViewById(R.id.btn_logo).setOnClickListener(this);
        findViewById(R.id.btn_title).setOnClickListener(this);
        findViewById(R.id.btn_subtitle).setOnClickListener(this);
        findViewById(R.id.btn_menu).setOnClickListener(this);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this, "navigation", Toast.LENGTH_LONG).show();
            }
        });
        mToolbar.inflateMenu(R.menu.title_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ToolbarActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.item_filter));
        mToolbar.showOverflowMenu();
        mToolbar.dismissPopupMenus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_navigation) {
            mToolbar.setNavigationIcon(R.drawable.nav_back);
        } else if (v.getId() == R.id.btn_logo) {
            mToolbar.setLogo(R.drawable.nav_user);
        } else if (v.getId() == R.id.btn_title) {
            mToolbar.setTitle("标题");
            mToolbar.setTitleTextAppearance(this, R.style.titleTextAppearance);
        } else if (v.getId() == R.id.btn_subtitle) {
            mToolbar.setSubtitle("副标题");
            mToolbar.setSubtitleTextAppearance(this, R.style.subtitleTextAppearance);
        }
    }

}
