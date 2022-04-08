package com.kay.prog.ayim.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kay.prog.ayim.data.models.CharacterEntity

@TypeConverters(TypeListConverter::class)
@Database(entities = [CharacterEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}