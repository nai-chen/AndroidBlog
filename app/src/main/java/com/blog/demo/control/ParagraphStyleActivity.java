package com.blog.demo.control;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.BulletSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.IconMarginSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;
import android.widget.TextView;

import com.blog.demo.R;

public class ParagraphStyleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_paragraph_style);

        lendingMarginStyle();
        bulletSpanStyle();
        quoteSpanStyle();
        drawableMarginSpanStyle();
        iconMarginSpanStyle();
        complexSpanStyle();
    }

    private void lendingMarginStyle() {
        TextView tv = findViewById(R.id.tv_lending_margin1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new LeadingMarginSpan.Standard(60), 0, ss.length(), 0);
        tv.setText(ss);

        tv = findViewById(R.id.tv_lending_margin2);
        ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new LeadingMarginSpan.Standard(60, 20), 0, ss.length(), 0);
        tv.setText(ss);
    }

    private void bulletSpanStyle() {
        TextView tv = findViewById(R.id.tv_bullet_span1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new BulletSpan(), 0, ss.length(), 0);
        tv.setText(ss);

        tv = findViewById(R.id.tv_bullet_span2);
        ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new BulletSpan(20, Color.RED), 0, ss.length(), 0);
        tv.setText(ss);
    }

    private void quoteSpanStyle() {
        TextView tv = findViewById(R.id.tv_quota_span1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new QuoteSpan(), 0, ss.length(), 0);
        tv.setText(ss);

        tv = findViewById(R.id.tv_quota_span2);
        ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new QuoteSpan(Color.RED), 0, ss.length(), 0);
        tv.setText(ss);
    }

    private void drawableMarginSpanStyle() {
        Drawable drawable = getDrawable(R.drawable.checkbox_s);

        TextView tv = findViewById(R.id.tv_drawable_margin_span1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new DrawableMarginSpan(drawable), 0, ss.length(), 0);
        tv.setText(ss);

        tv = findViewById(R.id.tv_drawable_margin_span2);
        ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new DrawableMarginSpan(drawable, 20), 0, ss.length(), 0);
        tv.setText(ss);
    }

    private void iconMarginSpanStyle() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checkbox_s);

        TextView tv = findViewById(R.id.tv_icon_margin_span1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new IconMarginSpan(bmp), 0, ss.length(), 0);
        tv.setText(ss);

        tv = findViewById(R.id.tv_icon_margin_span2);
        ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new IconMarginSpan(bmp, 20), 0, ss.length(), 0);
        tv.setText(ss);
    }

    private void complexSpanStyle() {
        TextView tv = findViewById(R.id.tv_complex_span1);
        SpannableString ss = new SpannableString(getString(R.string.poetry));
        ss.setSpan(new BulletSpan(), 0, ss.length(), 0);
        ss.setSpan(new LeadingMarginSpan.Standard(40, 0), 0, ss.length(), 0);
        tv.setText(ss);
    }

}
