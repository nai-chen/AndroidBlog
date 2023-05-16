package com.blog.demo.image.glide

import okhttp3.OkHttpClient

class NetworkUtil private constructor() {

    companion object {
        fun getOKHttpClient(): OkHttpClient = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = OkHttpClient()
    }

}