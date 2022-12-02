package com.example.aptoide.domain.model


import com.google.gson.annotations.SerializedName

data class All(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("info")
    val info: InfoX
)