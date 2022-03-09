package com.kay.prog.ayim.api

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("episode_id") val id: Long,
    @SerializedName("title")      val title: String,
    @SerializedName("season")     val season: Int,
    @SerializedName("air_date")   val airDate: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("episode")    val episode: Int,
    @SerializedName("series")     val series: String
)