package com.blog.demo.control.text;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.blog.demo.R;

public class TextViewRichTextActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);

        TextView textView = findViewById(R.id.text_view);
        String richText = "<font color=\"red\">红色样式</font><br />"
                + "<big>大号字样式</big><br />"
                + "<small>小号字样式</small><br />"
                + "<i>斜体样式</i><br />"
                + "<b>粗体样式</b><br />"
                + "<tt>等t宽t样式</tt><br />"
                + "<p>段落样式</p><br />"
                + "<a href=\"http://www.baidu.com\">百度一下</a>";
        textView.setText(Html.fromHtml(richText));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
