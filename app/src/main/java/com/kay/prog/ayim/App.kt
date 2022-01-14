package com.kay.prog.ayim

import android.app.Application
import androidx.room.Room
import com.kay.prog.ayim.database.AppDatabase

class App : Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        private var mInstance: App? = null
        val instance get() = mInstance!!
    }
}

val Any.Injector: App
    get() = App.instance