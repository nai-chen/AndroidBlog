package com.blog.demo.application;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2018/3/29.
 */

public class WindowManagerActivity extends Activity implements View.OnClickListener {
    private final static int REQUEST_OVERLAY_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_window_manager);

        findViewById(R.id.btn_show_alert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_alert) {
            final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);

            final ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.icon_wrong);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    wm.removeView(imageView);
                }
            });
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
            lp.format = PixelFormat.TRANSPARENT;
            lp.type = WindowManager.LayoutParams.TYPE_TOAST;
            lp.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            wm.addView(imageView, lp);
        }
    }

    public void askForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请授权！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {

            }
        }

    }

}
