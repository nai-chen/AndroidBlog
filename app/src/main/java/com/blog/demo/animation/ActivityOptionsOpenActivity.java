package com.blog.demo.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Window;

import com.blog.demo.R;

/**
 * Created by cn on 2017/8/24.
 */

public class ActivityOptionsOpenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_activity_options_open);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityCompat.finishAfterTransition(this);
    }
}
