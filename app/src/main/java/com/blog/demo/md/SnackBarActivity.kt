package com.blog.demo.md

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.blog.demo.LogTool.logi
import com.blog.demo.R
import com.google.android.material.snackbar.Snackbar

class SnackBarActivity : Activity(), View.OnClickListener {

    private val LOG_TAG = "SnackBarActivity"
    private var mSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_snack_bar)

        findViewById<Button>(R.id.btn_cancel).setOnClickListener(this)
        findViewById<Button>(R.id.btn_in_coordinator).setOnClickListener(this)
        findViewById<Button>(R.id.btn_in_relative).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_cancel) {
            mSnackbar?.dismiss()
        } else {
            mSnackbar = Snackbar.make(v, "Snackbar", Snackbar.LENGTH_LONG)
            mSnackbar?.show()

            if (v.id == R.id.btn_in_coordinator) {
                mSnackbar?.setAction("Confirm") {
                    Toast.makeText(this@SnackBarActivity, "onClick", Toast.LENGTH_LONG).show()
                }
                mSnackbar?.addCallback(object : Snackbar.Callback() {
                    override fun onShown(sb: Snackbar) {
                        logi(LOG_TAG, "onShown")
                    }

                    override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                        if (event == DISMISS_EVENT_SWIPE) {
                            logi(LOG_TAG, "swipe")
                        } else if (event == DISMISS_EVENT_ACTION) {
                            logi(LOG_TAG, "action")
                        } else if (event == DISMISS_EVENT_TIMEOUT) {
                            logi(LOG_TAG, "timeout")
                        } else if (event == DISMISS_EVENT_MANUAL) {
                            logi(LOG_TAG, "manual")
                        } else if (event == DISMISS_EVENT_CONSECUTIVE) {
                            logi(LOG_TAG, "consecutive")
                        }
                    }
                })
            }
        }
    }

}