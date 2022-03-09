package com.kay.prog.ayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val season: Int,
    val airDate: String,
    val characters: List<String>,
    val episode: Int,
    val series: String
)