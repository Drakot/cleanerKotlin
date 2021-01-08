package com.drakot.cleanerkotlin.data.local

import android.preference.PreferenceManager
import com.drakot.cleanerkotlin.App

class Preference {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(App.context)
    val prefRecipes = "recipes"


    fun saveRecipes(recipes: String) {
        val editor = prefs.edit()
        editor.putString(prefRecipes, recipes)
        editor.apply()
    }

    fun delete(pref: String) {
        prefs.edit().remove(pref).apply()
    }

    fun getRecipes(): String? {
        val recipes = prefs.getString(prefRecipes, null)
        return recipes
    }

    fun deleteAll() {
        delete(prefRecipes)
    }
}