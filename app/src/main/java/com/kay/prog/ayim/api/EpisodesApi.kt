package com.kay.prog.ayim.api

import io.reactivex.Observable
import retrofit2.http.GET

interface EpisodesApi {

    @GET("episodes")
    fun getEpisodes(): Observable<List<EpisodeDto>>

//    @GET("episodes/{episode_id}")
//    fun getEpisode(@Path("episode_id") episode_id: Long?): Single<List<Item>>
}