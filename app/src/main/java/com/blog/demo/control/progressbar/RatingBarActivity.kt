package com.blog.demo.control.progressbar

import android.app.Activity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import com.blog.demo.R

class RatingBarActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_progress_bar_rating_bar)

        val tvRatingBar: TextView = findViewById(R.id.tv_rating_bar)
        val ratingBar: RatingBar = findViewById(R.id.rating_bar)
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            tvRatingBar.text = "选择$rating"
        }
    }

}