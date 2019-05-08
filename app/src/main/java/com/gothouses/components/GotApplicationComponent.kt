package com.gothouses.components

import android.content.Context
import com.google.gson.Gson
import com.gothouses.api.GotService
import com.gothouses.modules.ContextModule
import com.gothouses.modules.GotServiceModule
import com.gothouses.modules.NetworkModule
import com.gothouses.scopes.GotApplicationScope
import dagger.Component

@GotApplicationScope
@Component(modules = [GotServiceModule::class, NetworkModule::class, ContextModule::class])
interface GotApplicationComponent {

    fun getContext(): Context

    fun getGotService(): GotService

    fun getGson(): Gson
}