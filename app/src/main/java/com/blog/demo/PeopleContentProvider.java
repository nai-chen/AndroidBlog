package com.blog.demo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by cn on 2017/2/10.
 */

public class PeopleContentProvider extends ContentProvider {
    private static final String LOGTAG      = "PeopleContentProvider";

    public static final String AUTHORITY    = "com.blog.demo.content";
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

    @Override
    public boolean onCreate() {
        helper = new PeopleSQLiteOpenHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        LogUtil.log(LOGTAG, "query");
        if (uriMatcher.match(uri) == PEOPLE) {
            // 查询所有people数据
            return helper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        } else if (uriMatcher.match(uri) == PEOPLE_ID) {
            // 查询所有指定id的people数据
            String id = uri.getPathSegments().get(1);
            return helper.getReadableDatabase().query(TABLE_NAME, projection, PeopleSQLiteOpenHelper.COL_ID +"=?",
                    new String[]{id}, null, null, sortOrder);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)) {
            case PEOPLE: // 集合类型 vnd.android.cursor.dir
                return "vnd.android.cursor.dir/com.blog.demo.people";
            case PEOPLE_ID: // 非集合类型 vnd.android.cursor.item
                return "vnd.android.cursor.item/com.blog.demo.people";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        LogUtil.log(LOGTAG, "insert");
        if (uriMatcher.match(uri) == PEOPLE) {
            long rowid = helper.getWritableDatabase().insert(TABLE_NAME, null, values);

            if (rowid > 0) {
                Uri rowUri = ContentUris.withAppendedId(uri, rowid);
                getContext().getContentResolver().notifyChange(rowUri, null);
                return rowUri;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        LogUtil.log(LOGTAG, "delete");

        if (uriMatcher.match(uri) == PEOPLE) {
            return helper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
        } else if (uriMatcher.match(uri) == PEOPLE_ID) {
            String id = uri.getPathSegments().get(1);
            return helper.getWritableDatabase().delete(TABLE_NAME,
                    PeopleSQLiteOpenHelper.COL_ID +"=?", new String[]{id});
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        LogUtil.log(LOGTAG, "update");

        if (uriMatcher.match(uri) == PEOPLE) {
            return helper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
        } else if (uriMatcher.match(uri) == PEOPLE_ID) {
            String id = uri.getPathSegments().get(1);
            return helper.getWritableDatabase().update(TABLE_NAME, values,
                    PeopleSQLiteOpenHelper.COL_ID +"=?", new String[]{id});
        }

        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

}
