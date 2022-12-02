package com.example.aptoide.domain.model


import com.google.gson.annotations.SerializedName

data class Datasets(
    @SerializedName("all")
    val all: All
)