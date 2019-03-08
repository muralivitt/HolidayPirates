package com.holidaypirates.model.jsonmodels
import com.google.gson.annotations.SerializedName



data class Comment(
    @SerializedName("postId") var postId: Int = 0,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("body") var body: String = ""
):ResponseModel