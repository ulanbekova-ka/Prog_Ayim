package com.kay.prog.ayim

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private var mInstance: App? = null
        val instance get() = mInstance!!
    }
}

val Any.Injector: App
    get() = App.instance