package com.blog.demo.control.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class ConstraintLayoutMarginActivity extends Activity {

    private boolean visibility = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_layout_constraint_margin);

        final TextView tvLeft = findViewById(R.id.tv_left);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                visibility = !visibility;
                tvLeft.setVisibility(visibility ? View.VISIBLE : View.GONE);
            }
        });
    }
}
