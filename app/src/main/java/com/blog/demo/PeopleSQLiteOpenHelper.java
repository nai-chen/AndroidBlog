package com.blog.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/10.
 */

public class PeopleSQLiteOpenHelper extends SQLiteOpenHelper {
    public final static String LOGTAG = "PeopleSQLiteOpenHelper";

    public final static String DB_NAME = "people.db";
    public final static String TABLE_NAME = "people";
    public final static int VERSION = 1;

    public final static String COL_ID   = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_ADDR = "addr";
    public final static String COL_AGE  = "age";

    public final static String TABLE_CREATE = "create table if not exists " + TABLE_NAME + "("
            + COL_ID + " integer primary key autoincrement not null,"
            + COL_NAME + " text not null, "
            + COL_ADDR + " text not null, "
            + COL_AGE + " integer "
            + ")";

    public PeopleSQLiteOpenHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    public PeopleSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        LogUtil.log(LOGTAG, "name = " + name + " version = " + version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtil.log(LOGTAG, "onCreate");
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.log(LOGTAG, "onUpgrade");
    }

    public List<People> query() {
        List<People> list = new ArrayList<People>();

        LogUtil.log(LOGTAG, "query1");
        Cursor cursor = getWritableDatabase().query(TABLE_NAME,
                new String[]{COL_ID, COL_NAME, COL_ADDR, COL_AGE}, null, null, null, null, null);
        LogUtil.log(LOGTAG, "query2");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String addr = cursor.getString(cursor.getColumnIndex(COL_ADDR));
            int age = cursor.getInt(cursor.getColumnIndex(COL_AGE));
            list.add(new People(id, name, addr, age));
        }
        cursor.close();
        return list;
    }

    public void add(String name, String addr, int age) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADDR, addr);
        values.put(COL_AGE, age);
        getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public void delete(int id) {
        LogUtil.log(LOGTAG, "delete id = " + id);
        getWritableDatabase().delete(TABLE_NAME, COL_ID + "=?", new String[]{Integer.toString(id)});
    }

    public void modify(int id, String name, String addr, int age) {
        LogUtil.log(LOGTAG, "modify id = " + id);
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ADDR, addr);
        values.put(COL_AGE, age);
        getWritableDatabase().update(TABLE_NAME, values, COL_ID + "=?", new String[]{Integer.toString(id)});
    }

    public void clear() {
        getWritableDatabase().delete(TABLE_NAME, null, null);
    }
}
