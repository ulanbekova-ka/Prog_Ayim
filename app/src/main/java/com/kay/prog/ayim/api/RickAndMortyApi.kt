package com.kay.prog.ayim.api

import io.reactivex.Observable
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun getAllCharacters(): Observable<Response>
}