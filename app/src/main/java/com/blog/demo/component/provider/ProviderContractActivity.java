package com.blog.demo.component.provider;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ProviderContractActivity extends Activity {

    private ListView mListView;
    private List<Person> mContent = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_provider_contract);

        mListView = findViewById(R.id.list_view);

        if (checkPermission()) {
            readContact();
        } else {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS }, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (checkPermission()) {
                readContact();
            } else {
                finish();
            }
        }
    }

    private void readContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(
                    ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
            list.add(name);
            mContent.add(new Person(id, name));
        }
        cursor.close();
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showContact(position);
            }
        });
    }

    private void showContact(int position) {
        Person person = mContent.get(position);

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + person.id,
                null, null);

        String numberStr = "";
        while (cursor.moveToNext()) {
            String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
            LogTool.logi("Contract", number);
            if (numberStr.length() == 0) {
                numberStr += number;
            } else {
                numberStr += ", " + number;
            }
        }
        cursor.close();
        Toast.makeText(ProviderContractActivity.this, person.name + ":" + numberStr, Toast.LENGTH_LONG).show();
    }

    private static class Person {
        int id;
        String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

}
