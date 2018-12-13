package com.blog.demo.control;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2018/4/2.
 */

public class SlidingPaneLayoutActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sliding_pane_layout);

        final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
        slidingPaneLayout.setCoveredFadeColor(Color.WHITE);
        slidingPaneLayout.setSliderFadeColor(Color.WHITE);

        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                LogUtil.log("SlidingPaneLayout", "onPanelSlide slideOffset = " + slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {
                LogUtil.log("SlidingPaneLayout", "onPanelOpened");
            }

            @Override
            public void onPanelClosed(View panel) {
                LogUtil.log("SlidingPaneLayout", "onPanelClosed");
            }
        });

        CheckBox cb = (CheckBox) findViewById(R.id.cb_slideable);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        findViewById(R.id.btn_open_pane).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                slidingPaneLayout.openPane();
            }
        });
        findViewById(R.id.btn_close_pane).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                slidingPaneLayout.closePane();
            }
        });
    }

}
