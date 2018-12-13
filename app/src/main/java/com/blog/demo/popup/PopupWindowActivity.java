package com.blog.demo.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/4/5.
 */

public class PopupWindowActivity extends Activity implements View.OnClickListener {

    private Button mBtnShowPopupWindow;
    private RadioGroup mRgShow;
    private RadioButton mRbShowAtLocation, mRbShowAsDropDown;
    private CheckBox mCbTouchable, mCbFocusable, mCbOutsideTouchable;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        mBtnShowPopupWindow = (Button) findViewById(R.id.btn_show_popup_window);
        mBtnShowPopupWindow.setOnClickListener(this);

        mRgShow = (RadioGroup) findViewById(R.id.rg_show);
        mRbShowAtLocation = (RadioButton) findViewById(R.id.rb_show_at_location);
        mRbShowAsDropDown = (RadioButton) findViewById(R.id.rb_show_as_drop_down);

        mCbTouchable = (CheckBox) findViewById(R.id.cb_touchable);
        mCbFocusable = (CheckBox) findViewById(R.id.cb_focusable);
        mCbOutsideTouchable = (CheckBox) findViewById(R.id.cb_outside_touchable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_popup_window:
                showPopupWindow();
                break;
            case R.id.tv_take_photo:
            case R.id.tv_take_picture:
            case R.id.tv_cancel:
                mPopupWindow.dismiss();
                break;
        }
    }

    private void showPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            return;
        }
        mPopupWindow = new PopupWindow(this);
        // 设置宽度
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置高度
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.product_bg)));
        View view = getLayoutInflater().inflate(R.layout.popup_window_picture, null, false);
        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_take_picture).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        // 设置界面
        mPopupWindow.setContentView(view);

        mPopupWindow.setAnimationStyle(R.style.popupwindow_animation_style);

        // true时界面可点
        mPopupWindow.setTouchable(mCbTouchable.isChecked());
        // true时PopupWindow处理返回键
        mPopupWindow.setFocusable(mCbFocusable.isChecked());
        // true时点击外部消失，如果touchable为false时，点击界面也消失
        mPopupWindow.setOutsideTouchable(mCbOutsideTouchable.isChecked());

        // dismiss监听器
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LogUtil.log("PopupWindowActivity", "setOnDismissListener");
                mPopupWindow = null;
                backgroundAlpha(1.0f);
            }
        });


        if (mRgShow.getCheckedRadioButtonId() == R.id.rb_show_at_location) {
            mPopupWindow.showAtLocation(mBtnShowPopupWindow, Gravity.BOTTOM, 0, 0);
        } else if (mRgShow.getCheckedRadioButtonId() == R.id.rb_show_as_drop_down) {
            mPopupWindow.showAsDropDown(mBtnShowPopupWindow);
        }
        backgroundAlpha(0.5f);
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
