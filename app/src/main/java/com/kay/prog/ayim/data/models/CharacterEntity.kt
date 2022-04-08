package com.kay.prog.ayim.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    val status: String,
    val species: String?,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String?
)