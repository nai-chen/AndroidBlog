package com.blog.demo.control.text;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

public class TextViewSpannableActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_text_view_spannable);

        TextView tvSpannableString = findViewById(R.id.tv_spannable_string);
        SpannableString ss = new SpannableString("普通文本红色字体蓝色背景");
        ss.setSpan(new ForegroundColorSpan(Color.RED), 4, 8, 0);
        ss.setSpan(new BackgroundColorSpan(Color.BLUE), 8, 12, 0);
        tvSpannableString.setText(ss);

        TextView tvClickSpan = findViewById(R.id.tv_click_span);
        SpannableString ssClick = new SpannableString("点击我");
        ssClick.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(TextViewSpannableActivity.this, "onClick", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ds.linkColor);
            }
        }, 0, 3, 0);
        tvClickSpan.setText(ssClick);
        // 点击事件，需要setMovementMethod
        tvClickSpan.setMovementMethod(LinkMovementMethod.getInstance());
//        tvClickSpan.setHighlightColor(Color.TRANSPARENT);
    }

}
