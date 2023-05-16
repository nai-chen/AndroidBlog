package com.blog.demo.image.photoview

import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import com.blog.demo.LogTool
import com.blog.demo.R
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewNormalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_photo_view_normal)

        var photoView: PhotoView = findViewById(R.id.photo_view)
        photoView.scaleType = ImageView.ScaleType.FIT_CENTER
        photoView.setImageResource(R.drawable.scenery)

//        photoView.setOnPhotoTapListener {_, x, y ->
//            LogTool.logi("PhotoView", "OnPhotoTap ($x, $y)")
//        }

        photoView.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                LogTool.logi("PhotoView", "onSingleTapConfirmed (${e.x}, ${e.y})")
                LogTool.logi("PhotoView", "onSingleTapConfirmed displayRect ${photoView.displayRect}")
                LogTool.logi("PhotoView", "onSingleTapConfirmed getImageViewWidth ${getImageViewWidth(photoView)}")
                LogTool.logi("PhotoView", "onSingleTapConfirmed getImageViewHeight ${getImageViewHeight(photoView)}")

                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                LogTool.logi("PhotoView", "onDoubleTap (${e.x}, ${e.y})")
                return true
            }

            override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                LogTool.logi("PhotoView", "onDoubleTapEvent (${e.x}, ${e.y})")
                return true
            }
        })

        photoView.setOnScaleChangeListener { scaleFactor, focusX, focusY ->
            LogTool.logi("PhotoView", "OnScaleChange scaleFactor $scaleFactor ($focusX, $focusY)")
        }

        photoView.setOnSingleFlingListener { e1, e2, velocityX, velocityY ->
            LogTool.logi("PhotoView", "OnSingleFling (${e1.x}, ${e1.y}), (${e2.x}, ${e2.y}) $velocityX $velocityY")
            true
        }

    }

    private fun getImageViewWidth(imageView: ImageView): Int {
        return imageView.width - imageView.paddingLeft - imageView.paddingRight
    }

    private fun getImageViewHeight(imageView: ImageView): Int {
        return imageView.height - imageView.paddingTop - imageView.paddingBottom
    }

}