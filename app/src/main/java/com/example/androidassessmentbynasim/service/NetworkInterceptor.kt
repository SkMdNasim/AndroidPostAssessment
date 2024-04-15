package com.hanatour.openchat.service

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import com.example.androidassessmentbynasim.R
import com.hanatour.openchat.utils.Helper
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request.Builder
import okhttp3.Response
import java.io.IOException

/**
 * This class is used to handle network related events on API Services
 **/
class NetworkInterceptor(private val context: Activity) : Interceptor {
    /**
     * intercept of class NetworkInterceptor.
     * */
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {


        if (!Helper.isNetwork(context)) {
            //Helper.showSomeThingWentWrongDialog(context)
            throw NetWorkErrorException()
        }
        val builder: Builder = chain.request().newBuilder()
        val response = chain.proceed(builder.build())
        return response
    }

    /**
     * This is the inner class of NetworkInterceptor class.
     * */
    inner class NetWorkErrorException : IOException() {
        override val message: String?
            get() = context?.resources?.getString(R.string.no_internet_connection)
    }
}

/*object InternetDialog {

    private var dialog: SingleButtonDialog? = null

    fun getSingleButtonDialog(context: Activity): SingleButtonDialog {
        return if (dialog == null) {
            dialog = SingleButtonDialog(context)
            dialog!!
        } else dialog!!
    }
}*/
