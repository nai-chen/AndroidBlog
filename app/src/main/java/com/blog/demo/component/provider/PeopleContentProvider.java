package com.blog.demo.component.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blog.demo.feature.storage.PeopleSQLiteOpenHelper;

public class PeopleContentProvider extends ContentProvider {
    private static final String AUTHORITY   = "com.blog.demo.people";
    private static final String TABLE_NAME  = PeopleSQLiteOpenHelper.TABLE_NAME;

    private static final int PEOPLE      = 1;
    private static final int PEOPLE_ID   = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "people", PEOPLE);
        uriMatcher.addURI(AUTHORITY, "people/#", PEOPLE_ID);
    }

    private PeopleSQLiteOpenHelper helper;

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(uriMatcher.match(uri)) {
            case PEOPLE: // 集合类型 vnd.android.cursor.dir
                return "vnd.android.cursor.dir/com.blog.demo.people";
            case PEOPLE_ID: // 非集合类型 vnd.android.cursor.item
                return "vnd.android.cursor.item/com.blog.demo.people";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        helper = new PeopleSQLiteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri) == PEOPLE) {
            // 查询所有people数据
            return helper.getReadableDatabase().query(TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
        } else if (uriMatcher.match(uri) == PEOPLE_ID) {
            // 查询所有指定id的people数据
            String id = uri.getPathSegments().get(1);
            return helper.getReadableDatabase().query(TABLE_NAME, projection,
                    PeopleSQLiteOpenHelper.COL_ID +"=?", new String[]{id},
                    null, null, sortOrder);
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == PEOPLE) {
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                db.beginTransaction();
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    db.setTransactionSuccessful();
                    return ContentUris.withAppendedId(uri, id);
                }
            } finally {
                db.endTransaction();
            }
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == PEOPLE_ID) {
            String id = uri.getPathSegments().get(1);

            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                db.beginTransaction();
                int result = db.delete(TABLE_NAME,
                        PeopleSQLiteOpenHelper.COL_ID +"=?", new String[]{id});
                db.setTransactionSuccessful();
                return result;
            } finally {
                db.endTransaction();
            }
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == PEOPLE_ID) {
            String id = uri.getPathSegments().get(1);

            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                db.beginTransaction();
                int result = db.update(TABLE_NAME, values,
                        PeopleSQLiteOpenHelper.COL_ID +"=?", new String[]{id});
                db.setTransactionSuccessful();
                return result;
            } finally {
                db.endTransaction();
            }
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

}
