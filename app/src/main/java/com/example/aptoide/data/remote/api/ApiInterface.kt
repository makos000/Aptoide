package com.example.aptoide.data.remote.api

import com.example.aptoide.domain.model.AppsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiRes.END_POINT)
    suspend fun getApps(): Response<AppsModel>
}