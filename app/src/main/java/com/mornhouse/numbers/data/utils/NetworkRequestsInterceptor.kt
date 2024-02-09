package com.mornhouse.numbers.data.utils

import com.mornhouse.numbers.data.utils.ApiConstants.HEADER_CONTENT_TYPE
import com.mornhouse.numbers.data.utils.ApiConstants.MEDIA_TYPE_JSON
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkRequestsInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return makeRequest(chain)
    }

    private fun makeRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, MEDIA_TYPE_JSON)
            .build()

        return chain.proceed(request)
    }
}