package com.blog.demo.application.storage.json

import com.blog.demo.People

class JsonData {
    var aBoolean = false
    var aInt = 0
    var aDouble = 0.0
    var aString: String? = null
    var aObject: People? = null
    var aStringArray: Array<String?> = arrayOf()
    var aObjectArray: Array<People?> = arrayOf()

    override fun toString(): String {
        return """
             aBoolean = $aBoolean,
             aInt = $aInt,
             aDouble = $aDouble,
             aString = $aString,
             aObject = ${aObject!!.description()},
             aStringArray = ${stringArrayToString(aStringArray)},
             aObjectArray = ${objectArrayToString(aObjectArray)}
             """.trimIndent()
    }

    private fun stringArrayToString(stringArray: Array<String?>): String {
        var string = ""
        for (str in stringArray) {
            string += "$str,"
        }
        return string.substring(0, string.length - 1)
    }

    private fun objectArrayToString(objectArray: Array<People?>): String {
        var string = ""
        for (people in objectArray) {
            string += people?.description() + ","
        }
        return string.substring(0, string.length - 1)
    }

}