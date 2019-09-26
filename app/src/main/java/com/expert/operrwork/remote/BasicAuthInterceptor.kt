package com.expert.operrwork.remote

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * Created by Akshay.
 * for login Authorization
 */
open class BasicAuthInterceptor(Username: String, Password: String) : Interceptor {

    private val credentials: String = Credentials.basic(Username, Password)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }
}