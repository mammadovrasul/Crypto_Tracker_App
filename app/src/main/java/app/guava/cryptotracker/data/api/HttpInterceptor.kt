package app.guava.cryptotracker.data.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by rasulmammadov on 14,June,2022
 */
class HttpInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        return chain.proceed(request.build())
    }
}