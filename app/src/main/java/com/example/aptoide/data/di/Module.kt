package com.example.aptoide.data.di

import android.content.Context
import androidx.room.Room
import com.example.aptoide.data.local.localds.LocalDSImpl
import com.example.aptoide.domain.local.localds.LocalDSInterface
import com.example.aptoide.data.local.room.AppDao
import com.example.aptoide.data.local.room.AppDatabase
import com.example.aptoide.data.remote.api.ApiInterface
import com.example.aptoide.data.remote.api.ApiRes
import com.example.aptoide.data.remote.remoteds.RemoteDSImpl
import com.example.aptoide.domain.remote.remoteds.RemoteDSInterface
import com.example.aptoide.data.repo.RepoImpl
import com.example.aptoide.domain.repo.RepoInterface
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiRes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetail(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun getRemoteDS(apiInterface: ApiInterface): RemoteDSInterface {
        return RemoteDSImpl(apiInterface)
    }

    @Provides
    fun getLocalDS(dao: AppDao): LocalDSInterface {
        return LocalDSImpl(dao)
    }

    @Provides
    fun getRepo(remote: RemoteDSInterface, local: LocalDSInterface): RepoInterface {
        return RepoImpl(remote, local)
    }

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "AppDatabase",
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: AppDatabase) = database.appDao()
}