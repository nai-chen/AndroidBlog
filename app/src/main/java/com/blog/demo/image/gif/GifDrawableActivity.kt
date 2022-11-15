package com.blog.demo.image.gif

import android.app.Activity
import android.os.Bundle
import com.blog.demo.R
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

class GifDrawableActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_gif_drawable)

        var ivGif: GifImageView = findViewById(R.id.iv_gif)
        var drawable = GifDrawable(assets, "sample.gif")
        ivGif.setImageDrawable(drawable)

//        drawable.loopCount = 2
//        drawable.setSpeed(0.5f)
    }

}