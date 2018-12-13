package com.blog.demo;

import android.net.Uri;

/**
 * Created by cn on 2017/2/10.
 */

public class Content {
    public static final String AUTHORITY = "com.blog.demo.content";

    public final static class People {
        public static final Uri CONTENT_URI     = Uri.parse("content://" + AUTHORITY + "/people");

        public final static class PeopleColumns {
            public static final String ID           = PeopleSQLiteOpenHelper.COL_ID;

            public static final String NAME         = PeopleSQLiteOpenHelper.COL_NAME;

            public static final String ADDR         = PeopleSQLiteOpenHelper.COL_ADDR;

            public static final String AGE          = PeopleSQLiteOpenHelper.COL_AGE;
        }
    }

}
