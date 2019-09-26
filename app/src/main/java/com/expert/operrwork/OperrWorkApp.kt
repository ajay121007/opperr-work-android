package com.expert.operrwork

import android.app.Application
import com.appzorro.ruapp.di.AppModule
import org.koin.android.ext.android.startKoin

class OperrWorkApp : Application() {

    companion object {
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModule.appModule()))
    }
}