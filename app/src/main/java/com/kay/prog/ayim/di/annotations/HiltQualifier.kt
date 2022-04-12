package com.kay.prog.ayim.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoInterceptorOkHttpClient