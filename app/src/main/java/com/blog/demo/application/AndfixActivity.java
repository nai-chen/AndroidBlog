package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2018/7/3.
 */

public class AndfixActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_andfix);

        findViewById(R.id.btn_andfix).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showAndFixToast();
            }
        });
    }

    private void showAndFixToast() {
        Toast.makeText(this, "after andfix", Toast.LENGTH_LONG).show();
    }
}
