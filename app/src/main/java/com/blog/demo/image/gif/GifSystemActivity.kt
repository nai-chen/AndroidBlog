package com.blog.demo.image.gif

import android.app.Activity
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import com.blog.demo.R

class GifSystemActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_gif_glide)

        var ivGif: ImageView = findViewById(R.id.iv_gif)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(assets, "sample.gif")
            Thread {
                val drawable = ImageDecoder.decodeDrawable(source)

                ivGif.post {
                    ivGif.setImageDrawable(drawable)

                    if (drawable is AnimatedImageDrawable) {
                        drawable.start()
                    }

                }
            }.start()
        }
    }

}