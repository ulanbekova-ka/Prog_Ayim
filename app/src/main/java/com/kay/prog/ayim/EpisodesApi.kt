package com.kay.prog.ayim

import io.reactivex.Observable
import retrofit2.http.GET

interface EpisodesApi {

    @GET("episodes")
    fun getEpisodes(): Observable<MutableList<Episode>>
}