package com.blog.demo.third.glide

import android.app.Activity
import android.os.Bundle
import com.blog.demo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GlideActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_glide)

        Glide.with(this).load(R.drawable.flow_1)
            .transform(RoundedCorners(resources.getDimensionPixelOffset(R.dimen.margin_dpi_20)))
            .into(findViewById(R.id.image_view1))

        Glide.with(this).load(GlideDownloadPic("xxx.jpg"))
            .error(getDrawable(R.drawable.flow_2))
            .into(findViewById(R.id.image_view2))
    }

}