package com.example.ktrotest.util

import io.ktor.client.statement.*
import kotlinx.serialization.Serializable

object Api {
    const val KEY ="154bc1716d28fb8a212340401c409a10"
    const val URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
    const val BASE_URL ="http://www.kobis.or.kr"
    const val ERROR_MSG = "error:"
}

@Serializable
data class HttpErrorMessage(val message: String? = null)
@Serializable
data class HttpError(val error: HttpErrorMessage? = null)

data class Response<T>(
    val httpResponse: HttpResponse? = null,
    val data: T? = null,
    val error: HttpError? = null,
    val throwable: Throwable? = null
)
