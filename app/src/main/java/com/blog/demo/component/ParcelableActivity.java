package com.blog.demo.component;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blog.demo.R;

public class ParcelableActivity extends Activity {

    private StringBuffer sBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);

        TextView textView = findViewById(R.id.text_view);

        readValue(getIntent().getByteArrayExtra("byte_array"));
        ParcelableValue value = getIntent().getParcelableExtra("parcelable_value");
        sBuffer.append("\n" + value.toString());
        textView.setText(sBuffer.toString());
    }

    public void readValue(byte[] bytes) {
        Parcel reply = Parcel.obtain();
        try {
            reply.unmarshall(bytes, 0, bytes.length);
            reply.setDataPosition(0);

            sBuffer.append("iValue = " + reply.readInt()) ;
            sBuffer.append("\ndValue = " + reply.readDouble());
            sBuffer.append("\nstring = " + reply.readString());
        } finally {
            reply.recycle();
        }
    }

}
