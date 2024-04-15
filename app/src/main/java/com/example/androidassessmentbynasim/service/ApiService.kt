package com.hanatour.openchat.interfaces

import com.hanatour.openchat.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("posts/")
    fun getListData(
    ): Call<List<Data>>
}
