package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/1/24.
 */

public class ViewStubActivity extends Activity implements View.OnClickListener {
    private ViewStub vsContainer1, vsContainer2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stubview);

        Button btn = (Button) findViewById(R.id.btn_show1);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.btn_show2);
        btn.setOnClickListener(this);
        
        vsContainer1 = (ViewStub) findViewById(R.id.vs_view1);
        vsContainer2 = (ViewStub) findViewById(R.id.vs_view2);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show1) {
            if (findViewById(R.id.vs_view1_inflate) == null) {
                vsContainer1.inflate();
            } else {
                Toast.makeText(this, "View has inflated", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_show2) {
            if (vsContainer2.getVisibility() != View.VISIBLE) {
                vsContainer2.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "View is visibility", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
