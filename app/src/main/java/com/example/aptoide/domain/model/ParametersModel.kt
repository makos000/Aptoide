package com.example.aptoide.domain.model

data class ParametersModel (
    val added: String? = "",
    val apkTags: List<String>? = listOf(),
    val downloads: Int? = 0,
    val graphic: String? = "",
    val icon: String? = "",
    val id: Int? = 0,
    val md5sum: String? = "",
    val modified: String? = "",
    val name: String? = "",
    val packageX: String? = "",
    val pdownloads: Int? = 0,
    val rating: Double? = 0.0,
    val size: Int? = 0,
    val storeId: Int? = 0,
    val storeName: String? = "",
    val updated: String? = "",
    val uptype: String? = "",
    val vercode: Int? = 0,
    val vername: String? = ""
)