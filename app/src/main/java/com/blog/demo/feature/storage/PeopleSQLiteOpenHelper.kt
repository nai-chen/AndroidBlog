package com.blog.demo.feature.storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.blog.demo.People

class PeopleSQLiteOpenHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    companion object {
        val DB_NAME = "people.db"
        val VERSION = 1
        val TABLE_NAME = "people"
        val COL_ID = "_id"
        val COL_NAME = "name"
        val COL_ADDR = "addr"
        val COL_AGE = "age"
    }

    private val TABLE_CREATE = ("create table if not exists " + TABLE_NAME + "("
            + COL_ID + " integer primary key autoincrement not null,"
            + COL_NAME + " text not null, "
            + COL_ADDR + " text not null, "
            + COL_AGE + " integer "
            + ")")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun query(): List<People> {
        val list: MutableList<People> = ArrayList()
        val cursor = readableDatabase.query(
            TABLE_NAME,
            null, null, null, null, null, null
        )
        while (cursor.moveToNext()) {
            val people = People(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDR)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COL_AGE)))
            list.add(people)
        }
        cursor.close()
        return list
    }

    fun query(id: Int): People? {
        var people: People? = null
        val cursor = readableDatabase.query(
            TABLE_NAME, null,
            "$COL_ID=?", arrayOf(Integer.toString(id)), null, null, null, null
        )
        if (cursor.moveToNext()) {
            people = People(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDR)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COL_AGE)))
        }
        cursor.close()
        return people
    }

    fun insert(name: String?, addr: String?, age: Int) {
        val values = ContentValues()
        values.put(COL_NAME, name)
        values.put(COL_ADDR, addr)
        values.put(COL_AGE, age)
        val db = writableDatabase
        try {
            db.beginTransaction()
            db.insert(TABLE_NAME, null, values)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun delete(id: Int) {
        val db = writableDatabase
        try {
            db.beginTransaction()
            db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(Integer.toString(id)))
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun update(id: Int, name: String?, addr: String?, age: Int) {
        val values = ContentValues()
        values.put(COL_NAME, name)
        values.put(COL_ADDR, addr)
        values.put(COL_AGE, age)
        val db = writableDatabase
        try {
            db.beginTransaction()
            db.update(TABLE_NAME, values, "$COL_ID=$id", null)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
}