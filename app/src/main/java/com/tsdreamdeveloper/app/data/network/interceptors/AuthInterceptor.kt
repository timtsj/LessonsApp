package com.tsdreamdeveloper.app.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(
    val provider: () -> String,
    val headerName: String
): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val reqBuilder = chain
            .request()
            .newBuilder()
            .addHeader(headerName, provider())

        return chain.proceed(reqBuilder.build())
    }
}