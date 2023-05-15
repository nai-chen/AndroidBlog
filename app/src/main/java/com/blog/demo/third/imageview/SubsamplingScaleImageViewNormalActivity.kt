package com.blog.demo.third.imageview

import android.app.Activity
import android.os.Bundle
import com.blog.demo.R
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class SubsamplingScaleImageViewNormalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_subsampling_scale_image_view)

        var imageView: SubsamplingScaleImageView = findViewById(R.id.image_view)
        imageView.setImage(ImageSource.resource(R.drawable.large_pic))
    }

}