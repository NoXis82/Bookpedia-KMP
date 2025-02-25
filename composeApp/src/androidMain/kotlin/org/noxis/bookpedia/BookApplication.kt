package org.noxis.bookpedia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.noxis.bookpedia.di.initKoin

class BookApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}