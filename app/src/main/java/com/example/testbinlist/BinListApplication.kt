package com.example.testbinlist

import android.app.Application
import com.example.testbinlist.di.dataModule
import com.example.testbinlist.di.repositoryModule
import com.example.testbinlist.di.uiModule
import com.example.testbinlist.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BinListApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BinListApplication)
            modules(dataModule, repositoryModule, useCaseModule, uiModule)
        }
    }
}