package com.blog.demo.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blog.demo.R;

public class GroupOneView extends LinearLayout {

    public GroupOneView(Context context) {
        super(context);
    }

    public GroupOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View.inflate(context, R.layout.view_group_one, this);

        TextView tv =  findViewById(R.id.text_view);
        tv.setText("This is group one.");
    }

}
