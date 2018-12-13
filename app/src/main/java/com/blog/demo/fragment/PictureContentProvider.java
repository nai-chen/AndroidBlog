package com.blog.demo.fragment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by cn on 2017/3/23.
 */

public class PictureContentProvider extends ContentProvider {
    public static final String PICTURES_TYPE = "vnd.android.cursor.dir/pictures";
    public static final String PICTURE_ITEM_TYPE = "vnd.android.cursor.item/picture";
    public static final String AUTHORITY = "cn.blog.demo.picture";

    public static final int PICTURES = 1;
    public static final int PICTURE  = 2;

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PATH = "path";

    public static final Uri PICTURE_URI =
            Uri.parse("content://" + AUTHORITY + "/picture");

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "pictures", PICTURES);
        uriMatcher.addURI(AUTHORITY, "picture/#", PICTURE);
    }
    private PictureSQLiteOpenHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new PictureSQLiteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mHelper.query(projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int code = uriMatcher.match(uri);
        switch (code) {
            case PICTURES:
                return PICTURES_TYPE;
            case PICTURE:
                return PICTURE_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mHelper.insert(values);
        return Uri.withAppendedPath(PICTURE_URI, Long.toString(id));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mHelper.delete(selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mHelper.update(values, selection, selectionArgs);
    }

    private static class PictureSQLiteOpenHelper extends SQLiteOpenHelper {
        public final static String DB_NAME = "pic.db";
        public final static int DB_VERSION = 1;
        public final static String TABLE_NAME = "picture";

        private static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME +
                "(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, " +
                " path TEXT" +
                ")";
        public PictureSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            return getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }

        public long insert(ContentValues values) {
            return getWritableDatabase().insert(TABLE_NAME, null, values);
        }

        public int delete(String selection, String[] selectionArgs) {
            return getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
        }

        public int update(ContentValues values, String selection, String[] selectionArgs) {
            return getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
        }

    }

}
