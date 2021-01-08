package com.drakot.cleanerkotlin.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.drakot.cleanerkotlin.App
import com.drakot.cleanerkotlin.data.Repository
import com.drakot.cleanerkotlin.data.model.RecipesResponse
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class ApiManager : Repository {

    val service: ApiService
        get() {
            val retrofit = RetrofitApiFactory(App.context).instanceJsonRetrofit()
            return retrofit.create(ApiService::class.java)
        }

    private fun isConnectedToInternet(): Boolean = getConnectionType(App.context) != 0

    private fun getConnectionType(context: Context): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = 3
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = 2
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = 1
                    } else if (type == ConnectivityManager.TYPE_VPN) {
                        result = 3
                    }
                }
            }
        }
        return result
    }

    fun <T> doRequest(
        call: Call<T>,
        onSuccess: (response: T) -> Unit,
        onError: (error: ErrorResponse) -> Unit
    ) {
        try {
            if (isConnectedToInternet()) {

                call.enqueue(object : retrofit2.Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (data != null) {
                                onSuccess(data)
                            } else {
                                onError(ErrorResponse.Error)
                            }
                        }
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        onError(ErrorResponse.Error)
                    }
                })

            } else {
                onError(ErrorResponse.NoConnection)
            }
        } catch (e: IOException) {
            onError(ErrorResponse.Error)
        }
    }

    override fun getRecipes(
        onSuccess: (item: RecipesResponse) -> Unit,
        errorResponse: (error: ErrorResponse) -> Unit
    ) {
        return doRequest(
            service.getRecipes(),
            onSuccess,
            errorResponse
        )

    }

    override fun saveRecipes(it: RecipesResponse) {

    }
}