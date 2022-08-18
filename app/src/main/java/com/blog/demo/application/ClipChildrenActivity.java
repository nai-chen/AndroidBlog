package com.blog.demo.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ClipChildrenActivity extends Activity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_clip_chilren);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PicturePageAdapter(this));

        viewPager = findViewById(R.id.view_pager_clip_pager);
        viewPager.setAdapter(new PicturePageAdapter(this));
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.margin_dpi_10));
        viewPager.setOffscreenPageLimit(2);

        final ViewPager vp = viewPager;
        findViewById(R.id.view_clip_pager).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                vp.dispatchTouchEvent(event);
                return true;
            }
        });
    }

    public static class PicturePageAdapter extends PagerAdapter {
        private List<ImageView> mImageViewList = new ArrayList<>();
        private int[] mPictureResource = new int[]{
                R.drawable.switcher1,
                R.drawable.switcher2,
                R.drawable.switcher3,
                R.drawable.switcher4,
                R.drawable.switcher5
        };

        public PicturePageAdapter(Context context) {
            for (int index = 0; index < mPictureResource.length; index++) {
                ImageView iv = new ImageView(context);
                iv.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                iv.setImageResource(mPictureResource[index]);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                mImageViewList.add(iv);
            }
        }

        // 返回界面数量
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            LogTool.logi("ClipChildrenActivity", "instantiateItem position = " + position);
            return mImageViewList.get(position);
        }

        // 去除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position));
            LogTool.logi("ClipChildrenActivity", "destroyItem position = " + position);
        }

    }

}
