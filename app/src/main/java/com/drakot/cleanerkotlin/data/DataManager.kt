package com.drakot.cleanerkotlin.data

import com.drakot.cleanerkotlin.data.local.LocalManager
import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.data.remote.ApiManager
import com.drakot.cleanerkotlin.data.remote.ErrorResponse

object DataManager {
    val apiManager: Repository = ApiManager()
    val localManager: Repository = LocalManager()

    fun getRecipes(
        response: (item: List<Recipe>) -> Unit,
        errorResponse: (error: ErrorResponse) -> Unit
    ) {
        apiManager.getRecipes({
            if (it.results.isNullOrEmpty()) {
                errorResponse(ErrorResponse.NoData)
            } else {
                localManager.saveRecipes(it)
                response(it.results!!)
            }

        }, {
            if (it == ErrorResponse.NoConnection) {
                callLocalRecipes(response, errorResponse)
            } else {
                errorResponse(it)
            }

        })
    }

    private fun callLocalRecipes(
        response: (item: List<Recipe>) -> Unit,
        errorResponse: (error: ErrorResponse) -> Unit
    ) {
        localManager.getRecipes({
            if (it.results.isNullOrEmpty()) {
                errorResponse(ErrorResponse.NoData)
            } else {
                response(it.results!!)
            }
        }, {
            errorResponse(it)
        })
    }
}