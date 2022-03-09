package com.kay.prog.ayim.database

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode")
    fun getAllEpisodes(): Observable<MutableList<Episode>>

    @Query("SELECT * FROM episode WHERE id = :id")
    fun getById(id: Long?): Single<Episode>

//    @Insert
//    fun insert(episode: Episode): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(episodes: List<Episode>)

//    @Update
//    fun update(episode: Episode): Completable
//
//    @Delete
//    fun delete(episode: Episode): Completable
}