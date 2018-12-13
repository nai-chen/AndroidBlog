package com.blog.demo.application;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/3.
 */

public class SpanCustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_span_custom);

        TextView tv = (TextView) findViewById(R.id.tv);
        SpannableString ss = new SpannableString("0123456789");
        ss.setSpan(new ToastSpan(this, "Toast Click"), 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ss);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static class ToastSpan extends ClickableSpan {
        private Context mContext;
        private CharSequence mMessage;

        ToastSpan(Context context, CharSequence message) {
            this.mContext = context;
            this.mMessage = message;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(mContext, mMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.RED);
            ds.setTextSize(ds.getTextSize() * 1.5f);
        }
    }

}
