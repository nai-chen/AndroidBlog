package com.blog.demo.component.provider

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blog.demo.LogTool
import com.blog.demo.R

class ProviderContractActivity : Activity() {

    private lateinit var mListView: ListView
    private val mContent: MutableList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_provider_contract)
        mListView = findViewById(R.id.list_view)

        if (checkPermission()) {
            readContact()
        } else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == 1) {
            if (checkPermission()) {
                readContact()
            } else {
                finish()
            }
        }
    }

    private fun readContact() {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        val list: MutableList<String> = ArrayList()
        while (cursor!!.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
            list.add(name)
            mContent.add(Person(id, name))
        }
        cursor.close()

        mListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        mListView.setOnItemClickListener { _, _, position, _ ->
            showContact(position)
        }
    }

    private fun showContact(position: Int) {
        val person = mContent[position]
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + person.id,
            null, null
        )

        var numberStr = ""
        while (cursor!!.moveToNext()) {
            val number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            LogTool.logi("Contract", number)
            numberStr += if (numberStr.isNotEmpty()) {
                number
            } else {
                ", $number"
            }
        }
        cursor.close()
        Toast.makeText(this@ProviderContractActivity, person.name + ":" + numberStr, Toast.LENGTH_LONG).show()
    }

    private inner class Person constructor(var id: Int, var name: String)

}