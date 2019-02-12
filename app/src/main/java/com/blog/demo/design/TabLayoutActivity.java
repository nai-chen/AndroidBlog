package com.blog.demo.design;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.blog.demo.R;

public class TabLayoutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_tab_layout);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        newTab(tabLayout);

        TabLayout tabLayoutText = findViewById(R.id.tab_layout_text);
        newTab(tabLayoutText);

        TabLayout tabLayoutIndicator = findViewById(R.id.tab_layout_indicator);
        newTab(tabLayoutIndicator);

        TabLayout tabLayoutGravityCenter = findViewById(R.id.tab_layout_gravity_center);
        newTab(tabLayoutGravityCenter);

        TabLayout tabLayoutGravityFill = findViewById(R.id.tab_layout_gravity_fill);
        newTab(tabLayoutGravityFill);

        TabLayout tabLayoutModeFixed = findViewById(R.id.tab_layout_tab_mode_fixed);
        newTabMode(tabLayoutModeFixed);

        TabLayout tabLayoutModeScrollable = findViewById(R.id.tab_layout_tab_mode_scrollable);
        newTabMode(tabLayoutModeScrollable);

        TabLayout tabLayoutTabItem = findViewById(R.id.tab_layout_tab_item);
        tabLayoutTabItem.addTab(tabLayoutTabItem.newTab().setCustomView(R.layout.layout_tab_item2));
    }

    private void newTab(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
    }

    private void newTabMode(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
        tabLayout.addTab(tabLayout.newTab().setText("tab5"));
        tabLayout.addTab(tabLayout.newTab().setText("tab6"));
        tabLayout.addTab(tabLayout.newTab().setText("tab7"));
        tabLayout.addTab(tabLayout.newTab().setText("tab8"));
        tabLayout.addTab(tabLayout.newTab().setText("tab9"));
    }

}
