package com.blog.demo.progress;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/10/27.
 */

public class RatingBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        final TextView textView = (TextView) findViewById(R.id.tv_info);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView.setText("选择" + rating);
            }
        });
    }

}
