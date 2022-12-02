package com.example.aptoide.data.local.localds

import com.example.aptoide.data.local.room.AppDao
import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.domain.local.localds.LocalDSInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDSImpl @Inject constructor(private val dao: AppDao): LocalDSInterface {
    override fun insertAppToDB(appEntity: AppEntity) {
        return dao.insertAppsToDB(appEntity)
    }

    override fun readAppsFromDB(): Flow<List<AppEntity>> {
        return dao.readAppsFromDB()
    }

    override fun nukeTable() {
        return dao.nukeTable()
    }
}