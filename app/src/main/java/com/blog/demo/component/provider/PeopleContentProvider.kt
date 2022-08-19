package com.blog.demo.component.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.blog.demo.feature.storage.PeopleSQLiteOpenHelper

class PeopleContentProvider : ContentProvider() {

    private val AUTHORITY = "com.blog.demo.people"
    private val TABLE_NAME = PeopleSQLiteOpenHelper.TABLE_NAME

    private val PEOPLE = 1
    private val PEOPLE_ID = 2

    private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init {
        uriMatcher.addURI(AUTHORITY, "people", PEOPLE)
        uriMatcher.addURI(AUTHORITY, "people/#", PEOPLE_ID)
    }

    private lateinit var helper: PeopleSQLiteOpenHelper

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            PEOPLE -> "vnd.android.cursor.dir/com.blog.demo.people"
            PEOPLE_ID -> "vnd.android.cursor.item/com.blog.demo.people"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun onCreate(): Boolean {
        helper = PeopleSQLiteOpenHelper(context)
        return true
    }

    override fun query(uri: Uri, projection: Array<String?>?, selection: String?, selectionArgs: Array<String?>?, sortOrder: String?): Cursor? {
        if (uriMatcher.match(uri) == PEOPLE) {
            // 查询所有people数据
            return helper.readableDatabase.query(
                TABLE_NAME, projection,
                selection, selectionArgs, null, null, sortOrder
            )
        } else if (uriMatcher.match(uri) == PEOPLE_ID) {
            // 查询所有指定id的people数据
            val id = uri.pathSegments[1]
            return helper.readableDatabase.query(
                TABLE_NAME, projection,
                PeopleSQLiteOpenHelper.COL_ID + "=?", arrayOf(id),
                null, null, sortOrder
            )
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (uriMatcher!!.match(uri) == PEOPLE) {
            val db = helper!!.writableDatabase
            try {
                db.beginTransaction()
                val id = db.insert(TABLE_NAME, null, values)
                if (id > 0) {
                    db.setTransactionSuccessful()
                    return ContentUris.withAppendedId(uri, id)
                }
            } finally {
                db.endTransaction()
            }
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String?>?): Int {
        if (uriMatcher.match(uri) == PEOPLE_ID) {
            val id = uri.pathSegments[1]
            val db = helper.writableDatabase
            return try {
                db.beginTransaction()
                val result = db.delete(TABLE_NAME, PeopleSQLiteOpenHelper.COL_ID + "=?", arrayOf(id))
                db.setTransactionSuccessful()
                result
            } finally {
                db.endTransaction()
            }
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String?>?): Int {
        if (uriMatcher.match(uri) == PEOPLE_ID) {
            val id = uri.pathSegments[1]
            val db = helper.writableDatabase
            return try {
                db.beginTransaction()
                val result = db.update(TABLE_NAME, values, PeopleSQLiteOpenHelper.COL_ID + "=?", arrayOf(id))
                db.setTransactionSuccessful()
                result
            } finally {
                db.endTransaction()
            }
        }
        throw IllegalArgumentException("Unsupported URI: $uri")
    }

}