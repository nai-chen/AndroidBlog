package com.blog.demo.text.textview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/3/28.
 */

public class TextViewLineSpaceActivity extends Activity {

    private String mText = "白日依山尽，\n黄河入海流。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_view_line_space);

        ((TextView)findViewById(R.id.tv_normal)).setText(mText);

        ((TextView)findViewById(R.id.tv_extra)).setText(mText);

        ((TextView)findViewById(R.id.tv_multiplier)).setText(mText);

        TextView tvSetting = (TextView)findViewById(R.id.tv_setting);
        tvSetting.setText(mText);
        tvSetting.setLineSpacing(20, 1.5f);
    }
}
