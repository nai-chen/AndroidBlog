package com.blog.demo.application.storage;

import android.net.Uri;

public class PeopleConstant {

    public final static Uri CONTENT_URI =
            Uri.parse("content://com.blog.demo.people/people");

    public final static class PeopleColumns {
        public final static String ID 	= "_id";
        public final static String NAME = "name";
        public final static String ADDR = "addr";
        public final static String AGE 	= "age";
    }

}
