package com.example.aptoide.domain.model


import com.google.gson.annotations.SerializedName

data class Responses(
    @SerializedName("listApps")
    val listApps: ListApps
)