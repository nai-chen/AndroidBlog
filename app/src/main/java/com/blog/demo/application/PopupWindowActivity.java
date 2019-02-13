package com.blog.demo.application;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class PopupWindowActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "PopupWindowActivity";
    private CheckBox mCbTouchable, mCbFocusable, mCbOutsideTouchable;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_popup_window);

        findViewById(R.id.btn_show_at_location).setOnClickListener(this);
        mCbTouchable = findViewById(R.id.cb_touchable);
        mCbFocusable = findViewById(R.id.cb_focusable);
        mCbOutsideTouchable = findViewById(R.id.cb_outside_touchable);
        findViewById(R.id.btn_show_drop_down).setOnClickListener(this);
    }

    private PopupWindow createPopupWindow() {
        PopupWindow popupWindow = new PopupWindow(this);
        // 设置宽度
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置高度
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorProduct)));
        View view = getLayoutInflater().inflate(R.layout.popup_window_picture, null, false);
        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_take_picture).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        // 设置界面
        popupWindow.setContentView(view);
        // true时界面可点
        popupWindow.setTouchable(true);
        // true时PopupWindow处理返回键
        popupWindow.setFocusable(true);
        // true时点击外部消失，如果touchable为false时，点击界面也消失
        popupWindow.setOutsideTouchable(true);

        // dismiss监听器
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LogTool.logi(LOG_TAG, "onDismiss");
                backgroundAlpha(1);
            }
        });
        return popupWindow;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_at_location) {
            PopupWindow popupWindow = createPopupWindow();
            // true时界面可点
            popupWindow.setTouchable(mCbTouchable.isChecked());
            // true时PopupWindow处理返回键
            popupWindow.setFocusable(mCbFocusable.isChecked());
            // true时点击外部消失，如果touchable为false时，点击界面也消失
            popupWindow.setOutsideTouchable(mCbOutsideTouchable.isChecked());
            popupWindow.setAnimationStyle(R.style.popup_window_animation_style);
//            backgroundAlpha(0.5f);
            popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);

            mPopupWindow = popupWindow;
        } else if (v.getId() == R.id.btn_show_drop_down) {
            PopupWindow popupWindow = createPopupWindow();
            // true时界面可点
            popupWindow.setTouchable(mCbTouchable.isChecked());
            // true时PopupWindow处理返回键
            popupWindow.setFocusable(mCbFocusable.isChecked());
            // true时点击外部消失，如果touchable为false时，点击界面也消失
            popupWindow.setOutsideTouchable(mCbOutsideTouchable.isChecked());
            popupWindow.showAsDropDown(v);

            mPopupWindow = popupWindow;
        } else if ((v.getId() == R.id.tv_take_photo)
                || (v.getId() == R.id.tv_take_picture)
                || (v.getId() == R.id.tv_cancel)) {
            LogTool.logi(LOG_TAG, "PopupWindow onClick");
            mPopupWindow.dismiss();
        }
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
