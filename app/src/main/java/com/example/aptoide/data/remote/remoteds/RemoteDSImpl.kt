package com.example.aptoide.data.remote.remoteds

import com.example.aptoide.data.remote.api.ApiInterface
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.domain.remote.remoteds.RemoteDSInterface
import com.example.aptoide.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDSImpl @Inject constructor(private val apiInterface: ApiInterface): RemoteDSInterface {
    override suspend fun getApps(): Resource<List<Parameters>> {
        try {
            val response = apiInterface.getApps()
            return if (response.isSuccessful){
                if (response.body() != null){
                    Resource.Success(response.body()!!.responses.listApps.datasets.all.data.list!!)
                } else{
                    Resource.Error("Response body is null")
                }
            }else{
                Resource.Error(response.message())
            }
        }catch (e: HttpException){
            return Resource.Error("Could not load")
        }
        catch (e: IOException){
            return Resource.Error("No internet connection established")
        }
    }
}