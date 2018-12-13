package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2018/1/26.
 */

public class ParcelActivity extends Activity implements View.OnClickListener {
    private TextView mTvContent;
    private byte[] mBytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parcel);

        findViewById(R.id.tv_write).setOnClickListener(this);
        findViewById(R.id.tv_read).setOnClickListener(this);
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_write:
                Parcel data = Parcel.obtain();
                try {
                    data.writeInt(12);
                    data.writeDouble(34.5);
                    data.writeString("This is a string");
                    mBytes = data.marshall();
                    mTvContent.setText(new String(mBytes));
                } finally {
                    data.recycle();
                }
                break;
            case R.id.tv_read:
                Parcel reply = Parcel.obtain();
                try {
                    reply.unmarshall(mBytes, 0, mBytes.length);
                    reply.setDataPosition(0);
                    mTvContent.setText(reply.readInt() + ":" +reply.readDouble() + ":" + reply.readString());
                } finally {
                    reply.recycle();
                }


                break;
        }
    }

    public void writeValue() {
        Parcel data = Parcel.obtain();
        try {
            data.writeInt(12);
            data.writeDouble(34.5);
            data.writeString("This is a string");
            byte[] bytes = data.marshall();
        } finally {
            data.recycle();
        }
    }

    public void readValue() {
        byte[] bytes = new byte[0];
        Parcel reply = Parcel.obtain();
        try {
            reply.unmarshall(bytes, 0, bytes.length);
            reply.setDataPosition(0);
            mTvContent.setText(reply.readInt() + ":" +reply.readDouble() + ":" + reply.readString());
        } finally {
            reply.recycle();
        }
    }

}
