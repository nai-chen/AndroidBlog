package com.blog.demo.md;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.R;
import com.google.android.material.snackbar.Snackbar;

public class FloatingActionButtonActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_floating_action_button);

        findViewById(R.id.btn_in_coordinator).setOnClickListener(this);
        findViewById(R.id.btn_in_relative).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Snackbar snackbar = Snackbar.make(v, "This is Snackbar", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
