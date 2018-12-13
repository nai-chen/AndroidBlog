package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2018/2/28.
 */

public class SoftInputManagerActivity extends Activity {

    private boolean mTranslate;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_soft_input_manager);

        mBtn = (Button) findViewById(R.id.btn);

        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LogUtil.log("SoftInputManagerActivity", "onLayoutChange");
                onLayout();
            }
        });

        getWindow().getDecorView().getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogUtil.log("SoftInputManagerActivity", "onGlobalLayout");
                        onLayout();

                        int[] location = new int[2];
//                        mBtnNextStep.getLocationOnScreen(location);
//                        int height = location[1] +
//                                + getResources().getDimensionPixelSize(R.dimen.margin_dpi_55);
//                        LogTool.logd("LoginActivity", "height = " + height);
//
//                        if (screenHeight > rect.bottom) {
//                            if (!mTranslate) {
//                                if (height > rect.bottom) {
//                                    mViewLogin.scrollBy(0, height - rect.bottom);
//                                    mTranslate = true;
//                                }
//                            }
//                        } else {
//                            if (mTranslate) {
//                                mViewLogin.scrollTo(0, 0);
//                                mTranslate = false;
//                            }
//                        }
                    }
                });
    }

    private void onLayout() {
        int screenHeight = getWindow().getDecorView().getHeight();
        LogUtil.log("SoftInputManagerActivity", "screenHeight = " + screenHeight);

        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        LogUtil.log("SoftInputManagerActivity", "rect.bottom = " + rect.bottom);

        if (screenHeight > rect.bottom) {
            if (!mTranslate) {
                // 获取按钮的左上角，按钮高度为40dp
                int[] location = new int[2];
                mBtn.getLocationOnScreen(location);
                int bottom = location[1] + getResources().getDimensionPixelSize(R.dimen.margin_xdpi_50);

                // 如果按钮被覆盖，移动整个界面向上移动
                if (bottom > rect.bottom) {
                    getWindow().getDecorView().scrollBy(0, bottom - rect.bottom);
                    mTranslate = true;
                }
            }
        } else {
            getWindow().getDecorView().scrollTo(0, 0);
            mTranslate = false;
        }

    }

    private boolean isShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        LogUtil.log("SoftInputManagerActivity", "screenHeight = " + screenHeight);

        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        LogUtil.log("SoftInputManagerActivity", "rect.bottom = " + rect.bottom);

        return screenHeight > rect.bottom;
    }

}
