package com.gothouses.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gothouses.scopes.GotApplicationScope
import dagger.Module
import dagger.Provides


@Module
class GsonModule {

    @Provides
    @GotApplicationScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


}