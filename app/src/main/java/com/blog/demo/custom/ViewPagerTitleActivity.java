package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blog.demo.R;
import com.blog.demo.control.viewpager.DemoPageAdapter;
import com.blog.demo.custom.control.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/10/20.
 */

public class ViewPagerTitleActivity extends Activity {
    private int[] mDrawableResIdArray = new int[]{
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_title);

        ViewPagerTitle title = (ViewPagerTitle) findViewById(R.id.view_pager_title);
        title.setTitle(new String[]{"标题0", "标题1", "标题2"});

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        title.setViewPager(viewPager);

        List<View> viewList = new ArrayList<View>();
        for (int resId : mDrawableResIdArray) {
            ImageView view = (ImageView) getLayoutInflater().inflate(R.layout.view_page_item, viewPager, false);
            viewList.add(view);
            view.setImageResource(resId);
        }
        viewPager.setAdapter(new DemoPageAdapter(viewList));
    }

}
