package com.blog.demo.application;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
        mPopupWindow = new PopupWindow(this);
        // 设置宽度
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置高度
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorProduct)));
        View view = getLayoutInflater().inflate(R.layout.popup_window_picture, null, false);
        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_take_picture).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        // 设置界面
        mPopupWindow.setContentView(view);
        // true时界面可点
        mPopupWindow.setTouchable(true);
        // true时PopupWindow处理返回键
        mPopupWindow.setFocusable(true);
        // true时点击外部消失，如果touchable为false时，点击界面也消失
        mPopupWindow.setOutsideTouchable(true);

        // dismiss监听器
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LogTool.logi(LOG_TAG, "onDismiss");
            }
        });
        return mPopupWindow;
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
            popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        } else if (v.getId() == R.id.btn_show_drop_down) {
            PopupWindow popupWindow = createPopupWindow();
            // true时界面可点
            popupWindow.setTouchable(mCbTouchable.isChecked());
            // true时PopupWindow处理返回键
            popupWindow.setFocusable(mCbFocusable.isChecked());
            // true时点击外部消失，如果touchable为false时，点击界面也消失
            popupWindow.setOutsideTouchable(mCbOutsideTouchable.isChecked());
            popupWindow.showAsDropDown(v);
        } else if ((v.getId() == R.id.tv_take_photo)
                || (v.getId() == R.id.tv_take_picture)
                || (v.getId() == R.id.tv_cancel)) {
            LogTool.logi(LOG_TAG, "PopupWindow onClick");
            mPopupWindow.dismiss();
        }
    }
}
