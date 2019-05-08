package com.gothouses

import android.app.Application
import com.gothouses.components.DaggerGotApplicationComponent
import com.gothouses.components.GotApplicationComponent
import com.gothouses.modules.ContextModule

class GotApplication : Application() {

    companion object {
        val TAG = "GotApplication"

    }

    lateinit var gotApplicationComponent: GotApplicationComponent

    override fun onCreate() {
        super.onCreate()


        gotApplicationComponent = DaggerGotApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }


}