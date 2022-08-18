package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

public class ViewPagerTransformerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_pager);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PicturePageAdapter(this));
        viewPager.setPageTransformer(true, new PicturePageTransformer());
    }

    public static class PicturePageAdapter extends PagerAdapter {
        private List<ImageView> mImageViewList = new ArrayList<>();
        private int[] mPictureResource = new int[]{
                R.drawable.switcher1, R.drawable.switcher2, R.drawable.switcher3
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
            return mImageViewList.get(position);
        }

        // 去除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position));
        }

    }

    private static class PicturePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            LogTool.logi("ViewPagerTransformerActivity", "position = " + position);

            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 0) {
                // 左右移动，并且移除时变透明
                page.setAlpha(1 + position);
            } else if (position < 1) {
                // 去除左右移动效果
                page.setTranslationX(-page.getWidth() * position);
                // 进入时变大，移除时变小
                page.setScaleX(1 - position/2);
                page.setScaleY(1 - position/2);
                page.setAlpha(1 - position);
            } else {
                page.setAlpha(0);
            }
        }
    }

}
