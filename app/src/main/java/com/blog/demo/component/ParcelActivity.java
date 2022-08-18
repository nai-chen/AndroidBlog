package com.blog.demo.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class ParcelActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_parcel);

        findViewById(R.id.btn_write_parcel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoParcelable();
            }
        });
    }

    private void gotoParcelable() {
        Intent intent = new Intent(this, ParcelableActivity.class);
        intent.putExtra("byte_array", writeValue());
        ParcelableValue value = new ParcelableValue(10, 12.3, "This is parcelable value");
        intent.putExtra("parcelable_value", value);
        startActivity(intent);
    }

    public byte[] writeValue() {
        Parcel data = Parcel.obtain();
        try {
            data.writeInt(12);
            data.writeDouble(34.5);
            data.writeString("This is a string");
            return data.marshall();
        } finally {
            data.recycle();
        }
    }

}
