package com.blog.demo.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blog.demo.R;

public class GroupTwoView extends LinearLayout {

    public GroupTwoView(Context context) {
        super(context);
    }

    public GroupTwoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextView tv = findViewById(R.id.text_view);
        tv.setText("This is group two.");
    }

}
