package com.blog.demo.feature;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class SpannableActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feature_spannable);

        EditText editText = findViewById(R.id.edit_text);
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(new ForegroundColorSpan(Color.RED), 3, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        editText.setText(ss);

        makeSystemSpan();
        makeCustomSpan();
    }

    private void makeSystemSpan() {
        TextView tv = findViewById(R.id.tv_foreground_color);
        tv.setText(makeSpan(new ForegroundColorSpan(Color.RED)));

        tv = findViewById(R.id.tv_background_color);
        tv.setText(makeSpan(new BackgroundColorSpan(Color.BLUE)));

        tv = findViewById(R.id.tv_strike_through);
        tv.setText(makeSpan(new StrikethroughSpan()));

        tv = findViewById(R.id.tv_under_line);
        tv.setText(makeSpan(new UnderlineSpan()));

        tv = findViewById(R.id.tv_url);
        tv.setText(makeSpan(new URLSpan("tel:1234567890")));
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        tv = findViewById(R.id.tv_style);
        tv.setText(makeSpan(new StyleSpan(Typeface.BOLD_ITALIC)));

        tv = findViewById(R.id.tv_typeface);
        tv.setText(makeSpan(new TypefaceSpan("monospace")));

        tv = findViewById(R.id.tv_sup);
        tv.setText(makeSpan(new SuperscriptSpan()));

        tv = findViewById(R.id.tv_sub);
        tv.setText(makeSpan(new SubscriptSpan()));

        tv = findViewById(R.id.tv_relative_size);
        tv.setText(makeSpan(new RelativeSizeSpan(2)));

        tv = findViewById(R.id.tv_absolute_size);
        tv.setText(makeSpan(new AbsoluteSizeSpan(32, true)));

        tv = findViewById(R.id.tv_scale_x);
        tv.setText(makeSpan(new ScaleXSpan(2)));

        tv = findViewById(R.id.tv_image);
        Drawable drawable = getResources().getDrawable(R.drawable.star);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tv.setText(makeSpan(new ImageSpan(drawable)));

        tv = findViewById(R.id.tv_multi_span);
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(new RelativeSizeSpan(2), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new URLSpan("tel:1234567890"), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss);
    }

    private CharSequence makeSpan(CharacterStyle style) {
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(style, 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

    private void makeCustomSpan() {
        TextView tv = findViewById(R.id.text_view);
        SpannableString ss = new SpannableString("点击我");
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SpannableActivity.this, "onClick",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ds.linkColor);
            }
        }, 0, 3, 0);
        tv.setText(ss);
        // 点击事件，需要setMovementMethod
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
}
