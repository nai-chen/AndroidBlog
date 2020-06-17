package com.blog.demo.feature.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blog.demo.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleSQLiteOpenHelper extends SQLiteOpenHelper {
    public final static String LOGTAG = "PeopleSQLiteOpenHelper";

    public final static String DB_NAME = "people.db";
    public final static String TABLE_NAME = "people";
    public final static int VERSION = 1;

    public final static String COL_ID   = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_ADDR = "addr";
    public final static String COL_AGE  = "age";

    public final static String TABLE_CREATE =
            "create table if not exists " + TABLE_NAME + "("
                    + COL_ID + " integer primary key autoincrement not null,"
                    + COL_NAME + " text not null, "
                    + COL_ADDR + " text not null, "
                    + COL_AGE + " integer "
                    + ")";

    public PeopleSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<People> query() {
        List<People> list = new ArrayList<>();

        Cursor cursor = getReadableDatabase().query(TABLE_NAME,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            People people = new People();
            people.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            people.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            people.addr = cursor.getString(cursor.getColumnIndex(COL_ADDR));
            people.age = cursor.getInt(cursor.getColumnIndex(COL_AGE));
            list.add(people);
        }
        cursor.close();
        return list;
    }

    public People query(int id) {
        People people = null;
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null,
                COL_ID + "=?", new String[]{Integer.toString(id)}, null, null, null, null);
        if (cursor.moveToNext()) {
            people = new People();
            people.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            people.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            people.addr = cursor.getString(cursor.getColumnIndex(COL_ADDR));
            people.age = cursor.getInt(cursor.getColumnIndex(COL_AGE));
        }
        cursor.close();
        return people;
    }

    public void insert(String name, String addr, int age) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADDR, addr);
        values.put(COL_AGE, age);

        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.insert(TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();
            db.delete(TABLE_NAME, COL_ID + "=?", new String[]{Integer.toString(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public void update(int id, String name, String addr, int age) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADDR, addr);
        values.put(COL_AGE, age);

        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.update(TABLE_NAME, values, COL_ID + "=" + id, null);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

}
