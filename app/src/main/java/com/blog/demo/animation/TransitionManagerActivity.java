package com.blog.demo.animation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/8/28.
 */

public class TransitionManagerActivity extends Activity implements View.OnClickListener {

    private Scene scene1;
    private Scene scene2;
    private boolean mChange = false;
    private boolean mSingle = false;

    private ViewGroup sceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_manager);

        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_change_bound).setOnClickListener(this);
        findViewById(R.id.btn_fade).setOnClickListener(this);
        findViewById(R.id.btn_slide).setOnClickListener(this);
        findViewById(R.id.btn_explode).setOnClickListener(this);
        findViewById(R.id.btn_transition_set).setOnClickListener(this);

        findViewById(R.id.jianshe).setOnClickListener(this);
        findViewById(R.id.gongshang).setOnClickListener(this);
        findViewById(R.id.shanghai).setOnClickListener(this);

        sceneRoot = (ViewGroup) findViewById(R.id.view_content);
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_transition1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_transition2, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                change();
                break;
            case R.id.btn_change_bound:
                changeBound();
                break;
            case R.id.btn_fade:
                fade();
                break;
            case R.id.btn_slide:
                slide();
                break;
            case R.id.btn_explode:
                explode();
                break;
            case R.id.btn_transition_set:
                set();
                break;
            case R.id.jianshe:
            case R.id.gongshang:
            case R.id.shanghai:
                delay(v);
                break;
        }
    }

    private void change() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;
        TransitionManager.go(scene);
    }

    private void changeBound() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;
        ChangeBounds cb = new ChangeBounds();
        TransitionManager.go(scene, cb);
    }

    private void fade() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;
        Fade fade = new Fade();
        TransitionManager.go(scene, fade);
    }

    private void slide() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;
        Slide slide = new Slide();
        TransitionManager.go(scene, slide);
    }

    private void explode() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;
        Explode explode = new Explode();
        TransitionManager.go(scene, explode);
    }

    private void set() {
        Scene scene = mChange ? scene1 : scene2;
        mChange = !mChange;

        TransitionSet set = new TransitionSet();
        set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);

        ChangeBounds cb = new ChangeBounds();
        cb.addTarget(R.id.gongshang);
        set.addTransition(cb);

        cb = new ChangeBounds();
        cb.addTarget(R.id.jianshe);
        set.addTransition(cb);

        cb = new ChangeBounds();
        cb.addTarget(R.id.shanghai);
        set.addTransition(cb);

        TransitionManager.go(scene, set);
    }

    private void delay(View view) {
        TransitionSet set = new TransitionSet();
        ChangeBounds cb = new ChangeBounds();
        set.addTransition(cb);
        Explode explode= new Explode();
        set.addTransition(explode);
        TransitionManager.beginDelayedTransition(sceneRoot, set);

        ImageView ivGongShang = (ImageView) findViewById(R.id.gongshang);
        ImageView ivShangHai = (ImageView) findViewById(R.id.shanghai);
        ImageView ivJianShe = (ImageView) findViewById(R.id.jianshe);

        setDelay(ivGongShang, view);
        setDelay(ivShangHai, view);
        setDelay(ivJianShe, view);

        mSingle = !mSingle;
    }

    private void setDelay(ImageView iv, View view) {
        if (mSingle) {
            if (iv == view) {
                ViewGroup.LayoutParams lp = iv.getLayoutParams();
                lp.width = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_25);
                lp.height = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_25);
                iv.setLayoutParams(lp);
            } else {
                iv.setVisibility(View.VISIBLE);
            }
        } else {
            if (iv == view) {
                ViewGroup.LayoutParams lp = iv.getLayoutParams();
                lp.width = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_50);
                lp.height = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_50);
                iv.setLayoutParams(lp);
            } else {
                iv.setVisibility(View.INVISIBLE);
            }
        }
    }

}
