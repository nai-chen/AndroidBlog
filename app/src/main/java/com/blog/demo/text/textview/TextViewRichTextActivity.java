package com.blog.demo.text.textview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/3/28.
 */

public class TextViewRichTextActivity extends Activity {
    private String mRichText = "<font color=\"red\">红色样式</font><br />"
            + "<big>大号字样式</big><br />"
            + "<small>小号字样式</small><br />"
            + "<i>斜体样式</i><br />"
            + "<b>粗体样式</b><br />"
            + "<tt>等t宽t样式</tt><br />"
            + "<p>段落样式</p><br />"
            + "<a href=\"http://www.baidu.com\">百度一下</a>";
    private String mLinkText = "百度一下http://www.baidu.com";

    private String mSpannableText = "普通文本红色字体普通文本蓝色背景普通文本可点击文本";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_view_rich_text);
        TextView tvRichText = (TextView) findViewById(R.id.tv_rich_text);
        tvRichText.setText(Html.fromHtml(mRichText));
        tvRichText.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tvLinkText = (TextView) findViewById(R.id.tv_link_text);
        tvLinkText.setText(mLinkText);

        TextView tvLinkText2 = (TextView) findViewById(R.id.tv_link_text2);
        tvLinkText2.setText(mLinkText);

        SpannableString ss = new SpannableString(mSpannableText);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 4, 8, 0);
        ss.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.blue)), 12, 16, 0);
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(TextViewRichTextActivity.this, "onClick", Toast.LENGTH_LONG).show();
            }
        }, 20, 25, 0);
        TextView tvSpannableText = (TextView) findViewById(R.id.tv_spannable_text);
        tvSpannableText.setText(ss);
        tvSpannableText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
