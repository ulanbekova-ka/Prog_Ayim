package com.kay.prog.ayim.di.modules

import android.content.Context
import androidx.room.Room
import com.kay.prog.ayim.data.database.AppDatabase
import com.kay.prog.ayim.data.database.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Singleton
    @Provides
    fun provideDatabase (
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }
}