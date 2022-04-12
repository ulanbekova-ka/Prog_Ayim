package com.kay.prog.ayim.di.modules

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.kay.prog.ayim.data.network.RickAndMortyApi
import com.kay.prog.ayim.di.annotations.InterceptorOkHttpClient
import com.kay.prog.ayim.di.annotations.NoInterceptorOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRickAndMortyApi(
        retrofit: Retrofit
    ) : RickAndMortyApi
    {
        return retrofit.create(RickAndMortyApi::class.java)
    }

    @Provides
    @InterceptorOkHttpClient
    fun provideRetrofitWithClient(
        okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @NoInterceptorOkHttpClient
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor : HttpLoggingInterceptor,
        httpHeaderLoggingInterceptor : HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(300L, TimeUnit.SECONDS)
            .readTimeout(300L, TimeUnit.SECONDS)
            .writeTimeout(300L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpHeaderLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp_body", message)
            }
        }).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    fun provideHttpHeaderLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp_header", message)
            }
        }).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.NONE)
    }
}