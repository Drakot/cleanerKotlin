package com.drakot.cleanerkotlin.data.remote

import com.drakot.cleanerkotlin.data.model.RecipesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api")
    fun getRecipes(): Call<RecipesResponse>
}