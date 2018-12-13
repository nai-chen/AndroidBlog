package com.blog.demo.component;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/14.
 */

public class ContentProviderContractActivity extends Activity {
    private ListView mLv;
    private List<Person> mContent = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_provider_contract);
        mLv = (ListView) findViewById(R.id.lv);

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        List<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(
                    ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
            list.add(name);
            mContent.add(new Person(id, name));
        }
        cursor.close();
        mLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = mContent.get(position);

                Cursor phone = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=?",
                        new String[]{Integer.toString(person.id)},
                        null);

                String toastString = "";
                while (phone.moveToNext()) {
                    String number = phone.getString(phone.getColumnIndex(
//                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                            "Mobile"));
                    LogUtil.log("Contract", number);
                    if (toastString.length() == 0) {
                        toastString += number;
                    } else {
                        toastString += ", " + number;
                    }
                }
                phone.close();
                Toast.makeText(ContentProviderContractActivity.this, toastString, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class Person {
        int id;
        String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
