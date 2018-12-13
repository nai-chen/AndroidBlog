package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/14.
 */

public class ViewPagerAdapterActivity extends Activity {
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
        }

        viewPager.setAdapter(new DemoPageAdapter(mViewList));
    }

}
