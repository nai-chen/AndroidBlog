package com.blog.demo.image.animation

import android.animation.*
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R

class PropertyAnimationActivity : Activity(), View.OnClickListener {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_property)

        findViewById<Button>(R.id.btn_value_animator).setOnClickListener(this)
        findViewById<Button>(R.id.btn_object_animator).setOnClickListener(this)
        findViewById<Button>(R.id.btn_property_values_holder).setOnClickListener(this)
        findViewById<Button>(R.id.btn_animator_set).setOnClickListener(this)

        findViewById<Button>(R.id.btn_play_sequentially).setOnClickListener(this)
        findViewById<Button>(R.id.btn_play).setOnClickListener(this)

        findViewById<Button>(R.id.btn_key_frames).setOnClickListener(this)
        findViewById<Button>(R.id.btn_type_evaluator).setOnClickListener(this)

        imageView = findViewById(R.id.image_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_value_animator) {
            val animator = ValueAnimator.ofFloat(1f, 0.5f, 1f)
            animator.duration = 3000
            animator.addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                imageView.scaleX = value
            }
            animator.start()
        } else if (v.id == R.id.btn_object_animator) {
            val animator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0.5f, 1f)
            animator.duration = 3000
            animator.start()
        } else if (v.id == R.id.btn_property_values_holder) {
            val xHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f, 1.0f)
            val yHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f, 1.0f)
            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                .setDuration(3000)
                .start()
        } else if (v.id == R.id.btn_animator_set) {
            val xAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f)
            val yAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f)
            val animator = AnimatorSet()
            animator.playTogether(xAnimator, yAnimator)
            animator.duration = 3000
            animator.start()
        } else if (v.id == R.id.btn_play_sequentially) {
            val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f, 1.0f)
            alphaAnimator.duration = 2000

            val xAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f)
            val yAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f)
            val scaleAnimator = AnimatorSet()
            scaleAnimator.playTogether(xAnimator2, yAnimator2)
            scaleAnimator.duration = 2000

            val rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
            rotationAnimator.duration = 2000

            val animator = AnimatorSet()
            animator.playSequentially(alphaAnimator, scaleAnimator, rotationAnimator)
            animator.start()
        } else if (v.id == R.id.btn_play) {
            val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f, 1.0f)
            alphaAnimator.duration = 2000

            val xScaleAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f)
            xScaleAnimator.duration = 2000
            val yScaleAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f)
            yScaleAnimator.duration = 2000

            val rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
            rotationAnimator.duration = 2000

            val animator = AnimatorSet()
            animator.play(xScaleAnimator).with(yScaleAnimator).after(alphaAnimator).before(rotationAnimator)
            animator.start()
        } else if (v.id == R.id.btn_key_frames) {
            val keyframe1 = Keyframe.ofFloat(0f, 1.0f)
            val keyframe2 = Keyframe.ofFloat(0.2f, 0.5f)
            val keyframe3 = Keyframe.ofFloat(0.4f, 1.0f)
            val keyframe4 = Keyframe.ofFloat(0.7f, 0f)
            val keyframe5 = Keyframe.ofFloat(1.0f, 1.0f)

            val xHolder = PropertyValuesHolder.ofKeyframe("scaleX", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5)
            val yHolder = PropertyValuesHolder.ofKeyframe("scaleY", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5)

            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                .setDuration(3000)
                .start()
        } else if (v.id == R.id.btn_type_evaluator) {
            val typeEvaluator: TypeEvaluator<Float> = object : TypeEvaluator<Float> {
                var evaluator = FloatEvaluator()
                override fun evaluate(fraction: Float, startValue: Float, endValue: Float): Float {
                    if (fraction <= 0.2f) {
                        return evaluator.evaluate(fraction / 0.2f, 1.0f, 0.5f)
                    } else if (fraction <= 0.4f) {
                        return evaluator.evaluate((fraction - 0.2f) / 0.2f, 0.5f, 1.0f)
                    } else if (fraction < 0.8f) {
                        return evaluator.evaluate((fraction - 0.4f) / 0.4f, 1.0f, 0f)
                    } else if (fraction <= 1) {
                        return evaluator.evaluate((fraction - 0.8f) / 0.2f, 0f, 1.0f)
                    }
                    return endValue
                }
            }
            val xHolder = PropertyValuesHolder.ofObject("scaleX", typeEvaluator, 0f, 1.0f)
            val yHolder = PropertyValuesHolder.ofObject("scaleY", typeEvaluator, 0f, 1.0f)
            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                .setDuration(3000)
                .start()
        }
    }
}