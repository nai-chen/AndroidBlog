package com.blog.demo.component

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.widget.Button
import com.blog.demo.R

class ParcelActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_parcel)
        findViewById<Button>(R.id.btn_write_parcel).setOnClickListener {
            gotoParcelable()
        }
    }

    private fun gotoParcelable() {
        val intent = Intent(this, ParcelableActivity::class.java)
        intent.putExtra("byte_array", writeValue())
        val value = ParcelableValue(10, 12.3, "This is parcelable value")
        intent.putExtra("parcelable_value", value)
        startActivity(intent)
    }

    private fun writeValue(): ByteArray? {
        val data = Parcel.obtain()
        return try {
            data.writeInt(12)
            data.writeDouble(34.5)
            data.writeString("This is a string")
            data.marshall()
        } finally {
            data.recycle()
        }
    }

}