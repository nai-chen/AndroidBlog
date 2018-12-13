package com.blog.demo.test.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

import com.blog.demo.fragment.PictureContentProvider;

/**
 * Created by cn on 2017/3/23.
 */

public class PictureContentProviderTestCase extends ProviderTestCase2<PictureContentProvider> {
    public static final String COLUMN_NAME = PictureContentProvider.COLUMN_NAME;
    public static final String COLUMN_PATH = PictureContentProvider.COLUMN_PATH;

    public static final String PATH_PREFIX = "/sdcard/storeage/a/b/";

    public PictureContentProviderTestCase() {
        super(PictureContentProvider.class, PictureContentProvider.AUTHORITY);
    }

    // 测试插入
    public void testInsert() {
        Uri uri = insert("pic1");
        Cursor cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertTrue(cursor.moveToNext());
    }

    // 测试查询
    public void testQuery() {
        String name1 = "pic1";
        Uri uri = insert(name1);
        Cursor cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(1, cursor.getCount());
        cursor.moveToNext();
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), name1);
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)), PATH_PREFIX + name1 + ".png");

        uri = insert("pic2");
        cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(2, cursor.getCount());

        uri = insert("pic3");
        cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(3, cursor.getCount());
    }

    // 测试删除
    public void testDelete() {
        Uri uri = insert("pic1");
        Cursor cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(1, cursor.getCount());

        getMockContentResolver().delete(uri, null, null);
        cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(0, cursor.getCount());
    }

    // 测试修改
    public void testUpdate() {
        String name1 = "pic1";
        String name2 = "pic2";
        Uri uri = insert(name1);
        Cursor cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(1, cursor.getCount());
        cursor.moveToNext();
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), name1);
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)), PATH_PREFIX + name1 + ".png");

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name2);
        values.put(COLUMN_PATH, PATH_PREFIX + name2 + ".png");
        getMockContentResolver().update(uri, values, null, null);
        cursor = getMockContentResolver().query(uri, null, null, null, null);
        assertEquals(1, cursor.getCount());
        cursor.moveToNext();
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), name2);
        assertEquals(cursor.getString(cursor.getColumnIndex(COLUMN_PATH)), PATH_PREFIX + name2 + ".png");
    }

    private Uri insert(String name) {
        Uri uri = PictureContentProvider.PICTURE_URI;
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PATH, PATH_PREFIX + name + ".png");
        return getMockContentResolver().insert(uri, values);
    }

}
