package com.blog.demo.md;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class SnackBarActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "SnackBarActivity";
    private Snackbar mSnackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_snack_bar);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_in_coordinator).setOnClickListener(this);
        findViewById(R.id.btn_in_relative).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            if (mSnackbar != null) {
                mSnackbar.dismiss();
            }
        } else {
            mSnackbar = Snackbar.make(v, "Snackbar", Snackbar.LENGTH_LONG);
            mSnackbar.show();

            if (v.getId() == R.id.btn_in_coordinator) {
                mSnackbar.setAction("Confirm", new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarActivity.this, "onClick",
                                Toast.LENGTH_LONG).show();
                    }
                });

                mSnackbar.addCallback(new Snackbar.Callback(){
                    public void onShown(Snackbar sb) {
                        LogTool.logi(LOG_TAG, "onShown");
                    }

                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        if (event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {
                            LogTool.logi(LOG_TAG, "swipe");
                        } else if (event == Snackbar.Callback.DISMISS_EVENT_ACTION) {
                            LogTool.logi(LOG_TAG, "action");
                        } else if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                            LogTool.logi(LOG_TAG, "timeout");
                        } else if (event == Snackbar.Callback.DISMISS_EVENT_MANUAL) {
                            LogTool.logi(LOG_TAG, "manual");
                        } else if (event == Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE) {
                            LogTool.logi(LOG_TAG, "consecutive");
                        }

                    }
                });
            }
        }
    }

}
