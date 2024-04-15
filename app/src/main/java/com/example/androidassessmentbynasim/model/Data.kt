package com.hanatour.openchat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * This class contains the data in the response of All chat room.
 * */
data class Data (
    @SerializedName("userId") internal var userId: Int?,
    @SerializedName("id") internal var id: Int?,
    @SerializedName("title") internal var title: String?,
    @SerializedName("body") internal var body: String?,
) : Serializable
