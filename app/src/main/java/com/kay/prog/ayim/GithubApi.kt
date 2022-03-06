package com.kay.prog.ayim

import io.reactivex.Observable
import retrofit2.http.GET

interface GithubApi {

    @GET("repositories")
    fun getRepositories(): Observable<RepoResult>
}