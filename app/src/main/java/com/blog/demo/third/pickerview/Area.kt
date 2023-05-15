package com.blog.demo.third.pickerview

class Area {

    var data: MutableList<AreaProvince>? = null

    class AreaProvince {
        var name: String? = null
        var city: MutableList<AreaCity>? = null
    }

    class AreaCity {
        var name: String? = null
        var area: MutableList<String>? = null
    }

}