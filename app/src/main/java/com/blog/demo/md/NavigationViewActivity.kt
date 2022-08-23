package com.blog.demo.md

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.blog.demo.R
import com.google.android.material.navigation.NavigationView

class NavigationViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_navigation_view)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.menu_address || menuItem.itemId == R.id.menu_more) {
                menuItem.isChecked = true
            }
            Toast.makeText(this@NavigationViewActivity, menuItem.title, Toast.LENGTH_SHORT).show()
            menuItem.title
            drawerLayout.closeDrawers()
            true
        }
    }

}