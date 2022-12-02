package com.example.aptoide.data.repo

import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.data.remote.remoteds.RemoteDSImpl
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.domain.repo.RepoInterface
import com.example.aptoide.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepo @Inject constructor(private val remote: RemoteDSImpl): RepoInterface {

    var list : MutableList<Parameters> = mutableListOf()

    var model = Parameters("",0,"", "",
        0,"","","","",0,
        0.0, 0,"","","",
        0,"")

    override suspend fun getApps(): Flow<Resource<List<AppEntity>>> = flow{
        emit(Resource.Loading())
        val response = getAppsFromApi()
        if (response is Resource.Success){
            emit(Resource.Success(listOf(AppEntity(listOf(model)))))
        }
        else{
            emit(Resource.Error("Error"))
        }

    }

    override fun insertAppsToDB(appEntity: AppEntity) {
        list.add(model)
    }

    override fun readAppsfromDB(): Flow<List<AppEntity>> {
        return flow { list }
    }

    override fun nukeTable() {
        list.clear()
    }

    override suspend fun getAppsFromApi(): Resource<List<Parameters>> {
        return remote.getApps()
    }
}