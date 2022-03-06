package com.kay.prog.ayim.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

    @GET("episodes")
    fun getEpisodes(): Observable<List<Episode>>

    @GET("episodes/{episode_id}")
    fun getEpisode(@Path("episode_id") episode_id: Long?): Single<List<Episode>>
}