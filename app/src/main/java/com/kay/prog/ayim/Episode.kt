package com.kay.prog.ayim

data class Episode(
    val episode_id: Long?,
    val title: String?,
    val season: Int?,
    val air_date: String?,
    val characters: List<String>,
    val episode: Int?,
    val series: String?
)