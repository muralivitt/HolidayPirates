package com.holidaypirates.model.jsonmodels

import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("name") var name: String = "",
        @SerializedName("username") var username: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("address") var address: Address = Address(),
        @SerializedName("phone") var phone: String = "",
        @SerializedName("website") var website: String = "",
        @SerializedName("company") var company: Company = Company()
):ResponseModel {

    data class Address(
            @SerializedName("street") var street: String = "",
            @SerializedName("suite") var suite: String = "",
            @SerializedName("city") var city: String = "",
            @SerializedName("zipcode") var zipcode: String = "",
            @SerializedName("geo") var geo: Geo = Geo()
    ) {

        data class Geo(
                @SerializedName("lat") var lat: String = "",
                @SerializedName("lng") var lng: String = ""
        )
    }


    data class Company(
            @SerializedName("name") var name: String = "",
            @SerializedName("catchPhrase") var catchPhrase: String = "",
            @SerializedName("bs") var bs: String = ""
    )
}