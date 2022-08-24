package com.blog.demo.feature.storage

import android.net.Uri

class PeopleConstant {

    companion object {
        val CONTENT_URI = Uri.parse("content://com.blog.demo.people/people")
    }

    class PeopleColumns {

        companion object {
            val ID 	    = "_id"
            val NAME    = "name"
            val ADDR    = "addr"
            val AGE 	= "age"
        }

    }

}