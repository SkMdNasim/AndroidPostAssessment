package com.hanatour.openchat.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.hanatour.openchat.model.Data
import com.hanatour.openchat.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListRepository {
    val apiResponse: MutableLiveData<Response<List<Data>>?> =
        MutableLiveData<Response<List<Data>>?>()


    fun getListData(
        activity: Activity,
        pageCount: Int,
    ) {

        val call: Call<List<Data>> = ApiClient(activity).getClient.getListData()

        call.enqueue(object : Callback<List<Data>> {

            override fun onResponse(
                call: Call<List<Data>>,
                response: Response<List<Data>>
            ) {
                apiResponse.value = response
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                apiResponse.value = null
            }
        })
    }
}
