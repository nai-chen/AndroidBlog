package com.blog.demo.image.gif

import android.app.Activity
import android.os.Bundle
import com.blog.demo.R

class GifMovieActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_gif_movie)

        var ivGif: GifImageView = findViewById(R.id.iv_gif)
        ivGif.setResource(assets.open("sample.gif"))

        ivGif.start()
    }

}