package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/14.
 */

public class ViewPagerTransformerActivity extends Activity {
    private List<View> mViewList = new ArrayList<>();
    private int[] mDrawableResIdArray = new int[]{
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        for (int resId : mDrawableResIdArray) {
            ImageView view = (ImageView) getLayoutInflater().inflate(
                    R.layout.view_page_item, viewPager, false);
            mViewList.add(view);
            view.setImageResource(resId);
            view.setId(resId);
            LogUtil.log("ViewPagerTransformerActivity", resId + "");
        }

        viewPager.setAdapter(new DemoPageAdapter(mViewList));
        viewPager.setPageTransformer(false, new DemoPageTransformer());
    }

    private class DemoPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            LogUtil.log("transformPage", page.getId() + ": position = " + position);
            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 0) {
                // 左右移动，并且移除时变透明
                page.setAlpha(1 + position);
            } else if (position < 1) {
                // 去除左右移动效果
                page.setTranslationX(-page.getWidth() * position);
                // 进入时变大，移除时变小
                page.setScaleX(1 - position);
                page.setScaleY(1 - position);
                page.setAlpha(1 - position);
            } else {
                page.setAlpha(0);
            }
        }
    }
}
