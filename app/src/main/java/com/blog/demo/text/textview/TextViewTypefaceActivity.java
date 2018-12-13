package com.blog.demo.text.textview;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.blog.demo.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cn on 2017/2/3.
 */

public class TextViewTypefaceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_view_typeface);

        TextView tvTypeFace = (TextView) findViewById(R.id.tv_typeface);
        setTypeFace(tvTypeFace);
    }

    private void setTypeFace(TextView tv) {
        final Typeface font = Typeface.createFromAsset(getAssets(),
                "fonts" + File.separator + "digital-7.ttf");
        tv.setTypeface(font);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        tv.setText(dateFormat.format(date.getTime()));
    }
}
