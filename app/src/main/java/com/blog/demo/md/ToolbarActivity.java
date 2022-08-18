package com.blog.demo.md;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.blog.demo.R;

public class ToolbarActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_toolbar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_set_navigation).setOnClickListener(this);
        findViewById(R.id.btn_set_logo).setOnClickListener(this);
        findViewById(R.id.btn_set_title).setOnClickListener(this);
        findViewById(R.id.btn_set_subtitle).setOnClickListener(this);
        findViewById(R.id.btn_set_background).setOnClickListener(this);
        findViewById(R.id.btn_set_overflow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_navigation) {
            toolbar.setNavigationIcon(R.drawable.nav_back);
        } else if (v.getId() == R.id.btn_set_logo) {
            toolbar.setLogo(R.mipmap.ic_launcher_round);
        } else if (v.getId() == R.id.btn_set_title) {
            toolbar.setTitle("New Title");
        } else if (v.getId() == R.id.btn_set_subtitle) {
            toolbar.setSubtitle("New Sub Title");
        } else if (v.getId() == R.id.btn_set_background) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.red));
        } else if (v.getId() == R.id.btn_set_overflow) {
            toolbar.setOverflowIcon(getDrawable(R.drawable.nav_more));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_faq) {
            Toast.makeText(this, "Faq", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_add) {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_setting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
