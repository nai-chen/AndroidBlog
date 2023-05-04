package com.blog.demo.image.gif

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.blog.demo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition

class GifGlideActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_gif_glide)

        var ivGif: ImageView = findViewById(R.id.iv_gif)
//        Glide.with(this)
//            .load("file:///android_asset/sample.gif")
//            .into(ivGif)

        Glide.with(this)
            .asGif()
            .load("file:///android_LOG_TAG/sample.gif")
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(
                        resource: GifDrawable,
                        transition: Transition<in GifDrawable>?) {
                    resource.setLoopCount(2)
                    ivGif.setImageDrawable(resource)

                    resource.start()
                }

            })
    }

}