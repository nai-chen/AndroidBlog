package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class SoftInputManagerActivity extends Activity {
    private static final String LOG_TAG = "SoftInputManagerActivity";

    private boolean mTranslate;
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_soft_input_manager);
        mBtn = findViewById(R.id.btn);

        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LogTool.logi(LOG_TAG, "onLayoutChange");
                onLayout();
            }
        });

        getWindow().getDecorView().getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogTool.logi(LOG_TAG, "onGlobalLayout");
                        onLayout();
                    }
                });
    }

    private void onLayout() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        LogTool.logi(LOG_TAG, "screenHeight = " + screenHeight);
        LogTool.logi(LOG_TAG, "rect.top = " + rect.top);
        LogTool.logi(LOG_TAG, "rect.bottom = " + rect.bottom);

        if (screenHeight - getNavigatorBarHeight() > rect.bottom) {
            // mTranslate用来记录是否已经移动
            if (!mTranslate) {
                // 获取按钮的左上角，按钮高度为40dp
                int[] location = new int[2];
                mBtn.getLocationOnScreen(location);

                int bottom = location[1] + getResources().getDimensionPixelSize(R.dimen.margin_dpi_50);
                LogTool.logi(LOG_TAG, "btn.bottom = " + location[1] + ", bottom = " + bottom);

                // 如果按钮被覆盖，移动整个界面向上移动
                if (bottom > rect.bottom) {
                    LogTool.logi(LOG_TAG, "scrollBy = " + (bottom - rect.bottom));
                    getWindow().getDecorView().scrollBy(0, bottom - rect.bottom);
                    mTranslate = true;
                }
            }
        } else {
            getWindow().getDecorView().scrollTo(0, 0);
            mTranslate = false;
        }
    }

    private int getNavigatorBarHeight() {
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);

        LogTool.logi(LOG_TAG, "Status Bar Height = " + height);

        return height;
    }

}
