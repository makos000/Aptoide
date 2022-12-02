package com.example.aptoide.domain.model


import com.google.gson.annotations.SerializedName

data class AppsModel(
    @SerializedName("responses")
    val responses: Responses,
    @SerializedName("status")
    val status: String
)