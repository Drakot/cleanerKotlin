package com.drakot.cleanerkotlin.data.local

import com.drakot.cleanerkotlin.data.Repository
import com.drakot.cleanerkotlin.data.model.RecipesResponse
import com.drakot.cleanerkotlin.data.remote.ErrorResponse
import com.google.gson.Gson

class LocalManager : Repository {
    private val preference = Preference()
    override fun getRecipes(
        onSuccess: (item: RecipesResponse) -> Unit,
        errorResponse: (error: ErrorResponse) -> Unit
    ) {

        val response =
            Gson().fromJson<RecipesResponse>(preference.getRecipes(), RecipesResponse::class.java)
        onSuccess(response)
    }

    override fun saveRecipes(it: RecipesResponse) {
        val json = Gson().toJson(it)
        preference.saveRecipes(json)
    }
}