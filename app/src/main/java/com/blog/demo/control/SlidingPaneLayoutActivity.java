package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;

import com.blog.demo.R;

public class SlidingPaneLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_sliding_pane_layout);

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
