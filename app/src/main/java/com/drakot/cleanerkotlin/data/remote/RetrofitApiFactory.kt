package com.drakot.cleanerkotlin.data.remote

import android.content.Context
import com.drakot.cleanerkotlin.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class RetrofitApiFactory(private val context: Context) {
    fun instanceJsonRetrofit(): Retrofit {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheDir = File(context.cacheDir, "HttpCache")
        val cache = Cache(cacheDir, cacheSize.toLong())
        val httpClient = httpClient()
        httpClient.cache(cache)
        httpClient.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder.build()
    }

    private fun httpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(
            TIME_OUT.toLong(),
            TimeUnit.SECONDS
        )
            .readTimeout(
                TIME_OUT.toLong(),
                TimeUnit.SECONDS
            )
            .writeTimeout(
                TIME_OUT.toLong(),
                TimeUnit.SECONDS
            )

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient
    }

    companion object {
        const val TIME_OUT = 30
        private val REWRITE_CACHE_CONTROL_INTERCEPTOR =
            Interceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", String.format("max-age=%d", 0))
                    .build()
            }
    }

}