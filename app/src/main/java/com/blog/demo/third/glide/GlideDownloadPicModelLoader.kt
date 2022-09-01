package com.blog.demo.third.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class GlideDownloadPicModelLoader : ModelLoader<GlideDownloadPic, InputStream> {

    class Factory : ModelLoaderFactory<GlideDownloadPic, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideDownloadPic, InputStream> {
            return GlideDownloadPicModelLoader()
        }

        override fun teardown() {
        }

    }

    override fun buildLoadData(
        model: GlideDownloadPic,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream> {
        return ModelLoader.LoadData(model, GlideDownloadPicDataFetcher(model))
    }

    override fun handles(model: GlideDownloadPic): Boolean {
        return true
    }

}