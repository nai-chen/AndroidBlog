package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.blog.demo.R;

public class ViewStubActivity extends Activity implements View.OnClickListener {
    private ViewStub viewStub;
    private TextView tvInflate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_view_stub);

        findViewById(R.id.btn_inflate).setOnClickListener(this);
        findViewById(R.id.btn_set_visibility).setOnClickListener(this);

        viewStub = findViewById(R.id.view_stub);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_inflate) {
            if (tvInflate == null) {
                tvInflate = (TextView) viewStub.inflate();
            }
        } else if (v.getId() == R.id.btn_set_visibility) {
            if (viewStub.getVisibility() != View.VISIBLE) {
                viewStub.setVisibility(View.VISIBLE);
            }
        }
    }

}
