package com.drakot.cleanerkotlin.data

import com.drakot.cleanerkotlin.data.model.RecipesResponse
import com.drakot.cleanerkotlin.data.remote.ErrorResponse

interface Repository {

    fun getRecipes(
        onSuccess: (item: RecipesResponse) -> Unit,
        errorResponse: (error: ErrorResponse) -> Unit
    )

    fun saveRecipes(it: RecipesResponse)
}