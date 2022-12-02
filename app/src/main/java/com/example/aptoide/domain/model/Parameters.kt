package com.example.aptoide.domain.model


import com.example.aptoide.data.local.room.AppEntity
import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("added")
    val added: String?,
    @SerializedName("downloads")
    val downloads: Int?,
    @SerializedName("graphic")
    val graphic: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("md5sum")
    val md5sum: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("package")
    val packageX: String?,
    @SerializedName("pdownloads")
    val pdownloads: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("store_id")
    val storeId: Int?,
    @SerializedName("store_name")
    val storeName: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("uptype")
    val uptype: String?,
    @SerializedName("vercode")
    val vercode: Int?,
    @SerializedName("vername")
    val vername: String?
)