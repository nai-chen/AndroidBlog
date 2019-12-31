package com.blog.demo.application.systemui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class SystemUiVisibilityActivity extends Activity {
    private static final String LOG_TAG = "SystemUiVisibilityActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_system_ui_visibility);

        final CheckBox cbProfile = findViewById(R.id.cb_profile);
        final CheckBox cbHideNavigation = findViewById(R.id.cb_hide_navigation);
        final CheckBox cbFullscreen = findViewById(R.id.cb_fullscreen);
        final CheckBox cbLayoutHideNavigation = findViewById(R.id.cb_layout_hide_navigation);
        final CheckBox cbLayoutFullscreen = findViewById(R.id.cb_layout_fullscreen);
        final CheckBox cbLayoutStable = findViewById(R.id.cb_layout_stable);
        final CheckBox cbImmersive = findViewById(R.id.cb_immersive);
        final CheckBox cbImmersiveSticky = findViewById(R.id.cb_immersive_sticky);
        final CheckBox cbLightStatusBar = findViewById(R.id.cb_light_status_bar);
        final CheckBox cbFitsSystemWindows = findViewById(R.id.cb_fits_system_windows);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int flag = View.SYSTEM_UI_FLAG_VISIBLE;
                if (cbProfile.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                    LogTool.logi(LOG_TAG, "PROFILE");
                }
                if (cbHideNavigation.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                    LogTool.logi(LOG_TAG, "HIDE_NAVIGATION");
                }
                if (cbFullscreen.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_FULLSCREEN;
                    LogTool.logi(LOG_TAG, "FULLSCREEN");
                }
                if (cbLayoutHideNavigation.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                    LogTool.logi(LOG_TAG, "LAYOUT_HIDE_NAVIGATION");
                }
                if (cbLayoutFullscreen.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    LogTool.logi(LOG_TAG, "LAYOUT_FULLSCREEN");
                }
                if (cbLayoutStable.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    LogTool.logi(LOG_TAG, "LAYOUT_STABLE");
                }
                if (cbImmersive.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_IMMERSIVE;
                    LogTool.logi(LOG_TAG, "IMMERSIVE");
                }
                if (cbImmersiveSticky.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                    LogTool.logi(LOG_TAG, "IMMERSIVE_STICKY");
                }
                if (cbLightStatusBar.isChecked()) {
                    flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    LogTool.logi(LOG_TAG, "LIGHT_STATUS_BAR");
                }

                Intent intent = new Intent(SystemUiVisibilityActivity.this, SystemUiVisibilitySampleActivity.class);
                intent.putExtra("value", flag);
                intent.putExtra("fitsSystemWindows", cbFitsSystemWindows.isChecked());
                startActivity(intent);
            }
        });
    }

}
