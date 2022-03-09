package com.kay.prog.ayim.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@TypeConverters(TypeListConverter::class)
@Database(entities = [Episode::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
}

class TypeListConverter {
    @TypeConverter
    fun listToJson(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}