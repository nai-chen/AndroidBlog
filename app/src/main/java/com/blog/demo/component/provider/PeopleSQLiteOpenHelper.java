package com.blog.demo.component.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

}
