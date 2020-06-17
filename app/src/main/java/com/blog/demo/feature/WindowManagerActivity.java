package com.blog.demo.feature;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.demo.R;

public class WindowManagerActivity extends Activity {

    private static final int REQUEST_OVERLAY_PERMISSION = 1;
    private View mSuspensionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_window_manager);

        findViewById(R.id.btn_show_suspension_window).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.icon_link);
        mSuspensionView = imageView;
    }

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请授权！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
        } else {
            showSuspensionWindow(mSuspensionView);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                showSuspensionWindow(mSuspensionView);
            }
        }
    }


    private void showSuspensionWindow(View view) {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL; // 窗口位置
        lp.format = PixelFormat.TRANSPARENT; // 位图格式
        lp.type = WindowManager.LayoutParams.TYPE_TOAST; // 窗口的层级关系
        lp.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 窗口的模式
        wm.addView(view, lp);
    }

}
