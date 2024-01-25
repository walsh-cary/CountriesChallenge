package com.walmart.countrieschallenge.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("capital")
    val capital: String = "",
)

typealias Countries = List<CountryModel>
