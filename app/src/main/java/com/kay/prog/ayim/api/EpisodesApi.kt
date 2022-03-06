package com.kay.prog.ayim.api

import io.reactivex.Observable
import retrofit2.http.GET

interface EpisodesApi {

    @GET("episodes")
    fun getEpisodes(): Observable<List<Episode>>
}