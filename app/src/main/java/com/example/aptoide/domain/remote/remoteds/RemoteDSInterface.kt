package com.example.aptoide.domain.remote.remoteds

import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.util.Resource

interface RemoteDSInterface {
    suspend fun getApps():Resource<List<Parameters>>
}