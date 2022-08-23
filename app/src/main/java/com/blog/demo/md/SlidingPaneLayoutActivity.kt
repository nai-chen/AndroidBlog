package com.blog.demo.md

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.slidingpanelayout.widget.SlidingPaneLayout.SimplePanelSlideListener
import com.blog.demo.R

class SlidingPaneLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_sliding_pane_layout)

        val slidingPaneLayout: SlidingPaneLayout = findViewById(R.id.sliding_pane_layout)
        val viewMenu: ViewGroup = findViewById(R.id.layout_menu)
        val viewContent: View = findViewById(R.id.layout_content)

        slidingPaneLayout.setPanelSlideListener(object : SimplePanelSlideListener() {

            override fun onPanelSlide(panel: View, slideOffset: Float) {
                val width = viewContent.width
                val offsetWidth = viewMenu.width * slideOffset
                val scale = (width - offsetWidth) / width
                viewContent.scaleX = scale
                viewContent.scaleY = scale
                viewContent.translationX = width * (scale - 1) / 2
            }

        })
    }

}