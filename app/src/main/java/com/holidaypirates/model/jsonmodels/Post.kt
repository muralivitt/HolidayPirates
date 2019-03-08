package com.holidaypirates.model.jsonmodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Post(
        @SerializedName("userId") var userId: Int = 0,
        @SerializedName("id") var id: Int = 0,
        @SerializedName("title") var title: String = "",
        @SerializedName("body") var body: String = ""
) : ResponseModel, Serializable