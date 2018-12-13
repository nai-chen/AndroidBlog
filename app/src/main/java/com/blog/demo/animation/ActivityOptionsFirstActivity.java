package com.blog.demo.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;

import com.blog.demo.R;

/**
 * Created by cn on 2017/8/21.
 */

public class ActivityOptionsFirstActivity extends Activity {
    private View mViewShared1, mViewShared2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_activity_options_first);

        mViewShared1 = findViewById(R.id.view_shared1);
        mViewShared2 = findViewById(R.id.view_shared2);

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNext();
            }
        });
    }

    private void gotoNext() {
        Intent intent = new Intent(this, ActivityOptionsSecondActivity.class);
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, Pair.create(mViewShared1, "robot1"), Pair.create(mViewShared2, "robot2"));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }

}
