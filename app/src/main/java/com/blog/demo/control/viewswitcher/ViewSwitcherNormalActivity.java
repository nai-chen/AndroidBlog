package com.blog.demo.control.viewswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ViewSwitcher;

import com.blog.demo.R;

public class ViewSwitcherNormalActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_switcher_normal);

        final ViewSwitcher viewSwitcher = findViewById(R.id.view_switcher);
        findViewById(R.id.btn_show_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
            }
        });

    }
}
