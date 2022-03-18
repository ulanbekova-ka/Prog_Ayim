package com.kay.prog.ayim.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characterentity")
    fun getAll(): LiveData<List<CharacterEntity>>

    @Query("SELECT * FROM characterentity WHERE id = :id")
    fun getById(id: Long?): Single<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(characterEntity: List<CharacterEntity>)
}