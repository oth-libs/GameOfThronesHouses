package com.gothouses.modules

import com.google.gson.Gson
import com.gothouses.api.GotService
import com.gothouses.api.GotServiceTask
import com.gothouses.scopes.GotApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [NetworkModule::class, GsonModule::class])
class GotServiceModule {

    @Provides
    @GotApplicationScope
    fun gotServiceTask(gotService: GotService): GotServiceTask {
        return GotServiceTask(gotService)
    }

    @Provides
    @GotApplicationScope
    fun gotService(retrofit: Retrofit): GotService {
        return retrofit.create(GotService::class.java)
    }

    @Provides
    @GotApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.anapioficeandfire.com/api/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}