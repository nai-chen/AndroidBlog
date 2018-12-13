package com.blog.demo.application;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
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
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/3.
 */

public class SpanCommonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_span_common);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(makeSpan(new ForegroundColorSpan(Color.RED)));

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(makeSpan(new BackgroundColorSpan(Color.BLUE)));

        TextView tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText(makeSpan(new StrikethroughSpan()));

        TextView tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText(makeSpan(new UnderlineSpan()));

        TextView tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setText(makeSpan(new URLSpan("tel:1234567890")));
        tv5.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setText(makeSpan(new StyleSpan(Typeface.BOLD_ITALIC)));

        TextView tv7 = (TextView) findViewById(R.id.tv7);
        tv7.setText(makeSpan(new TypefaceSpan("monospace")));

        TextView tv8 = (TextView) findViewById(R.id.tv8);
        tv8.setText(makeSpan(new SuperscriptSpan()));

        TextView tv9 = (TextView) findViewById(R.id.tv9);
        tv9.setText(makeSpan(new SubscriptSpan()));

        TextView tv10 = (TextView) findViewById(R.id.tv10);
        tv10.setText(makeSpan(new RelativeSizeSpan(2)));

        TextView tv11 = (TextView) findViewById(R.id.tv11);
        tv11.setText(makeSpan(new AbsoluteSizeSpan(32, true)));

        TextView tv12 = (TextView) findViewById(R.id.tv12);
        tv12.setText(makeSpan(new ScaleXSpan(2)));

        TextView tv13 = (TextView) findViewById(R.id.tv13);
        Drawable drawable = getResources().getDrawable(R.drawable.star);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tv13.setText(makeSpan(new ImageSpan(drawable)));

        TextView tv14 = (TextView) findViewById(R.id.tv14);
        tv14.setText(makeSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)));

        TextView tv15 = (TextView) findViewById(R.id.tv15);
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(new RelativeSizeSpan(2), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new URLSpan("tel:1234567890"), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv15.setMovementMethod(LinkMovementMethod.getInstance());

        tv15.setText(ss);
    }

    private CharSequence makeSpan(CharacterStyle style) {
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(style, 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }
}
