package com.example.androidassessmentbynasim.viewModel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hanatour.openchat.model.Data
import com.hanatour.openchat.repository.MainListRepository
import retrofit2.Response

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    private var map: Application
    private var repository: MainListRepository
    var apiResponse: MutableLiveData<Response<List<Data>>?> =
        MutableLiveData<Response<List<Data>>?>()

    init {
            map = app
        repository =  MainListRepository()
        apiResponse = repository.apiResponse
    }

    fun getListData(
        activity: Activity,
        pageCount: Int,
    ) {
            repository.getListData(activity, pageCount)

    }

}