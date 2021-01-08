package com.drakot.cleanerkotlin.data.model

import java.io.Serializable

class Recipe : Serializable {
    var title: String? = null
    var href: String? = null
    var ingredients: String? = null
    var thumbnail: String? = null
}