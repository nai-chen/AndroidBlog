package com.blog.demo.control.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blog.demo.R;

public class RatingBarActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_progress_bar_rating_bar);

        final TextView tvRatingBar = findViewById(R.id.tv_rating_bar);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRatingBar.setText("选择" + rating);
            }
        });
    }
}
