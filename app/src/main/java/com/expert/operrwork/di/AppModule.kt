package com.appzorro.ruapp.di
import com.expert.operrwork.util.SharedPref
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object AppModule {
    fun appModule(): Module = module {
        single { SharedPref(get()) }
    }
}