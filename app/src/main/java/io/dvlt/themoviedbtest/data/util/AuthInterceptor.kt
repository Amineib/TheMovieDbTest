package io.dvlt.themoviedbtest.data.util

import io.dvlt.themoviedbtest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(authorizaton, bearer + BuildConfig.tmdbApiKeyV4)
            .build()
        return chain.proceed(newRequest)
    }

    private companion object {
        const val bearer = "Bearer "
        const val authorizaton = "Authorization"
    }
}