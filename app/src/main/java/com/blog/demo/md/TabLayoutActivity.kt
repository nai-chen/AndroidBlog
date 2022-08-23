package com.blog.demo.md

import android.app.Activity
import android.os.Bundle
import com.blog.demo.R
import com.google.android.material.tabs.TabLayout

class TabLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_tab_layout)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        newTab(tabLayout)

        val tabLayoutText: TabLayout = findViewById(R.id.tab_layout_text)
        newTab(tabLayoutText)

        val tabLayoutIndicator: TabLayout = findViewById(R.id.tab_layout_indicator)
        newTab(tabLayoutIndicator)

        val tabLayoutGravityCenter: TabLayout = findViewById(R.id.tab_layout_gravity_center)
        newTab(tabLayoutGravityCenter)

        val tabLayoutGravityFill: TabLayout = findViewById(R.id.tab_layout_gravity_fill)
        newTab(tabLayoutGravityFill)

        val tabLayoutModeFixed: TabLayout = findViewById(R.id.tab_layout_tab_mode_fixed)
        newTabMode(tabLayoutModeFixed)

        val tabLayoutModeScrollable: TabLayout = findViewById(R.id.tab_layout_tab_mode_scrollable)
        newTabMode(tabLayoutModeScrollable)

        val tabLayoutTabItem: TabLayout = findViewById(R.id.tab_layout_tab_item)
        tabLayoutTabItem.addTab(tabLayoutTabItem.newTab().setCustomView(R.layout.layout_tab_item2))
    }

    private fun newTab(tabLayout: TabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("tab1"))
        tabLayout.addTab(tabLayout.newTab().setText("tab2"))
        tabLayout.addTab(tabLayout.newTab().setText("tab3"))
    }

    private fun newTabMode(tabLayout: TabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("tab1"))
        tabLayout.addTab(tabLayout.newTab().setText("tab2"))
        tabLayout.addTab(tabLayout.newTab().setText("tab3"))
        tabLayout.addTab(tabLayout.newTab().setText("tab4"))
        tabLayout.addTab(tabLayout.newTab().setText("tab5"))
        tabLayout.addTab(tabLayout.newTab().setText("tab6"))
        tabLayout.addTab(tabLayout.newTab().setText("tab7"))
        tabLayout.addTab(tabLayout.newTab().setText("tab8"))
        tabLayout.addTab(tabLayout.newTab().setText("tab9"))
    }

}