package com.kay.prog.ayim.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kay.prog.ayim.data.models.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(characterEntity: List<CharacterEntity>)
}