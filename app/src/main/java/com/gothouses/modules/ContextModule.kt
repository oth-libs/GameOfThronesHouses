package com.gothouses.modules

import android.content.Context
import com.gothouses.scopes.GotApplicationScope
import dagger.Module
import dagger.Provides


@Module
class ContextModule(private val context: Context) {


    @Provides
    @GotApplicationScope
    fun context(): Context {
        return context
    }
}