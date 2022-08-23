package com.blog.demo.md

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blog.demo.R

class ToolbarActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_toolbar)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        findViewById<Button>(R.id.btn_set_navigation).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_logo).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_title).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_subtitle).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_background).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_overflow).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_set_navigation) {
            toolbar.setNavigationIcon(R.drawable.nav_back)
        } else if (v.id == R.id.btn_set_logo) {
            toolbar.setLogo(R.mipmap.ic_launcher_round)
        } else if (v.id == R.id.btn_set_title) {
            toolbar.title = "New Title"
        } else if (v.id == R.id.btn_set_subtitle) {
            toolbar.subtitle = "New Sub Title"
        } else if (v.id == R.id.btn_set_background) {
            toolbar.setBackgroundColor(resources.getColor(R.color.red))
        } else if (v.id == R.id.btn_set_overflow) {
            toolbar.overflowIcon = getDrawable(R.drawable.nav_more)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_faq) {
            Toast.makeText(this, "Faq", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.item_add) {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.item_setting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}