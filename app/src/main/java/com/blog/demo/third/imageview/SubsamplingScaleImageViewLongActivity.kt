package com.blog.demo.third.imageview

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.ImageViewState
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class SubsamplingScaleImageViewLongActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_subsampling_scale_image_view)

        var imageView: SubsamplingScaleImageView = findViewById(R.id.image_view)

        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.long_pic)
        var dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
        imageView.minScale = bitmap.width/dm.widthPixels.toFloat()
        imageView.setImage(ImageSource.bitmap(bitmap), ImageViewState(0f, PointF(0f, 0f), 0))
    }

}