package com.blog.demo.popup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2018/3/19.
 */

public class SnackbarActivity extends Activity implements View.OnClickListener {

    private Snackbar mSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snack_bar);

        findViewById(R.id.btn_show_snackbar).setOnClickListener(this);

        findViewById(R.id.btn_click).setOnClickListener(this);
        findViewById(R.id.tv_faq).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_snackbar:
                mSnackbar = Snackbar.make(v, R.string.app_name, Snackbar.LENGTH_LONG)
                        .setAction("点击", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackbarActivity.this, "点击", Toast.LENGTH_LONG).show();
                            }
                        }).setActionTextColor(Color.GREEN)
                        .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                switch (event) {
                                    case BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_SWIPE:
                                        LogUtil.log("Snackbar", "SWIPE");
                                        break;
                                    case BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_ACTION:
                                        LogUtil.log("Snackbar", "ACTION");
                                        break;
                                    case BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_TIMEOUT:
                                        LogUtil.log("Snackbar", "TIMEOUT");
                                        break;
                                    case BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_MANUAL:
                                        LogUtil.log("Snackbar", "MANUAL");
                                        break;
                                    case BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_CONSECUTIVE:
                                        LogUtil.log("Snackbar", "CONSECUTIVE");
                                        break;
                                }
                            }
                            @Override
                            public void onShown(Snackbar transientBottomBar) {
                                super.onShown(transientBottomBar);
                                LogUtil.log("Snackbar", "onShown");
                            }
                        });
                mSnackbar.show();
                break;
            case R.id.btn_click:
                mSnackbar.dismiss();
                break;
        }
    }
}
