package com.blog.demo.md;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.blog.demo.R;

public class NavigationViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_navigation_view);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_address
                        || menuItem.getItemId() == R.id.menu_more) {
                    menuItem.setChecked(true);
                }
                Toast.makeText(NavigationViewActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                menuItem.getTitle();

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

}
