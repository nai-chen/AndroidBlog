package com.blog.demo.image.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.blog.demo.R;

public class LayoutAnimationActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_layout);
        findViewById(R.id.btn_start_animation).setOnClickListener(this);
        findViewById(R.id.btn_custom_layout_control).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_animation) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_from_right);
            LayoutAnimationController controller = new LayoutAnimationController(animation);
            controller.setDelay(0.1f);
            controller.setOrder(LayoutAnimationController.ORDER_NORMAL);

            ViewGroup container = findViewById(R.id.container);
            container.setLayoutAnimation(controller);
            container.startLayoutAnimation();
        } else if (v.getId() == R.id.btn_custom_layout_control) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_from_right);
            LayoutAnimationController controller = new CustomLayoutAnimationController(animation);
            controller.setDelay(0.1f);
            controller.setOrder(-1);

            ViewGroup container = findViewById(R.id.container);
            container.setLayoutAnimation(controller);
            container.startLayoutAnimation();
        }
    }

    private static class CustomLayoutAnimationController extends LayoutAnimationController {

        CustomLayoutAnimationController(Animation animation) {
            super(animation);
        }

        @Override
        protected int getTransformedIndex(AnimationParameters params) {
            if (getOrder() < 0) {
                int index = params.index;
                int count = (params.count + 1) / 2;

                if (index < count) {
                    return count - 1 - params.index;
                } else {
                    return params.index - count;
                }
            } else {
                return super.getTransformedIndex(params);
            }
        }
    }

}
