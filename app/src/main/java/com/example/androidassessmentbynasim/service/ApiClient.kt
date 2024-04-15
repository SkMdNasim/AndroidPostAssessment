package com.hanatour.openchat.service

import android.app.Activity
import android.content.Context
import com.google.gson.GsonBuilder
import com.hanatour.openchat.interfaces.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class is used to handle API Service throughout application
 **/
class ApiClient(private val context: Context) {

    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    internal val getClient: ApiService
        get() {
            val networkInterceptor = NetworkInterceptor(context as Activity)
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
            //if (BuildConfig.DEBUG)
                clientBuilder.addInterceptor(interceptor)
            val client = clientBuilder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ApiService::class.java)
        }

}