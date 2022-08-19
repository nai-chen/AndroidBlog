package com.blog.demo.component

import android.app.Activity
import android.os.Bundle
import android.os.Parcel
import android.widget.TextView
import com.blog.demo.R

class ParcelableActivity : Activity() {

    private val sBuffer = StringBuffer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        val textView: TextView = findViewById(R.id.text_view)
        readValue(intent.getByteArrayExtra("byte_array"))
        val value = intent.getParcelableExtra<ParcelableValue>("parcelable_value")
        sBuffer.append("\n${value.toString()}")

        textView.text = sBuffer.toString()
    }

    private fun readValue(bytes: ByteArray?) {
        val reply = Parcel.obtain()
        try {
            reply.unmarshall(bytes!!, 0, bytes.size)
            reply.setDataPosition(0)
            sBuffer.append("iValue = ${reply.readInt()}")
            sBuffer.append("\ndValue = ${reply.readDouble()}")
            sBuffer.append("\nstring = ${reply.readString()}")
        } finally {
            reply.recycle()
        }
    }

}