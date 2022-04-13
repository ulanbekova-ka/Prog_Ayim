package com.kay.prog.ayim.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWithInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWithoutInterceptor