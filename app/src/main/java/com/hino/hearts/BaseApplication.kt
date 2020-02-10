package com.hino.hearts

import android.app.Application
import com.hino.hearts.util.module.moduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Dihardja Software on 2020-02-07.
 */
class BaseApplication: Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                listOf(
//                    moduleDatabase,
                    moduleViewModel
                )
            )
        }
    }
}