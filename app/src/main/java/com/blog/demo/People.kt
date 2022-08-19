package com.blog.demo

class People constructor(var id: Int, var name: String? = null, var addr: String?, var age: Int) {

    constructor() : this(null, null)

    constructor(name: String?, addr: String?) : this(0, name, addr, 0)

    fun description(): String {
        return  "$id:$name:$addr:$age"
    }

}