package com.blog.demo.md;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.blog.demo.R;

public class SlidingPaneLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_sliding_pane_layout);

        SlidingPaneLayout slidingPaneLayout = findViewById(R.id.sliding_pane_layout);
        final View viewMenu = findViewById(R.id.layout_menu);
        final View viewContent = findViewById(R.id.layout_content);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.SimplePanelSlideListener(){
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                int width = viewContent.getWidth();
                float offsetWidth = viewMenu.getWidth() * slideOffset;

                float scale = (width - offsetWidth) / width;
                viewContent.setScaleX(scale);
                viewContent.setScaleY(scale);
                viewContent.setTranslationX(width * (scale - 1) / 2);
            }
        });
    }
}
