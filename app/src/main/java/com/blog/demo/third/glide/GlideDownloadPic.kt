package com.blog.demo.third.glide

import com.bumptech.glide.load.Key
import com.bumptech.glide.load.model.GlideUrl
import java.security.MessageDigest

class GlideDownloadPic(val picUrl: String) : Key {
    private var mGlideUrl = GlideUrl(picUrl)

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        mGlideUrl.updateDiskCacheKey(messageDigest)
    }

}