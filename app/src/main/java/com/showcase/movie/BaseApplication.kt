package com.hino.movie

import android.app.Application
import com.hino.hearts.oneSignal.MyNotificationOpenedHandler
import com.hino.hearts.util.module.moduleViewModel
import com.onesignal.OneSignal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Dihardja Software on 2020-02-07.
 */
class BaseApplication: Application() {

    companion object {
        lateinit var instance: _root_ide_package_.com.hino.movie.BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        _root_ide_package_.com.hino.movie.BaseApplication.Companion.instance = this

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .setNotificationOpenedHandler(MyNotificationOpenedHandler(this))
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

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