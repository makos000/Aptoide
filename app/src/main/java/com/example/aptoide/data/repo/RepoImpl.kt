package com.example.aptoide.data.repo

import com.example.aptoide.domain.local.localds.LocalDSInterface
import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.domain.remote.remoteds.RemoteDSInterface
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.domain.repo.RepoInterface
import com.example.aptoide.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepoImpl @Inject constructor(val remote: RemoteDSInterface, val local: LocalDSInterface):
    RepoInterface {
    override suspend fun getApps(): Flow<Resource<List<AppEntity>>> = flow {
        emit(Resource.Loading())
        readAppsfromDB().collect { data ->

                val response = getAppsFromApi()
                if (response is Resource.Success){
                    nukeTable()
                    insertAppsToDB(AppEntity(response.data!!))

                    readAppsfromDB().collect {
                        emit(Resource.Success(it))
                    }
                } else {
                    emit(Resource.Error(response.message!!))
                }

        }
    }

    override fun insertAppsToDB(appEntity: AppEntity) {
        return local.insertAppToDB(appEntity)
    }

    override fun readAppsfromDB(): Flow<List<AppEntity>> {
        return local.readAppsFromDB()
    }

    override fun nukeTable() {
        return local.nukeTable()
    }

    override suspend fun getAppsFromApi(): Resource<List<Parameters>> {
        return remote.getApps()
    }
}