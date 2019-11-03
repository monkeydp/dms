package com.monkeydp.daios.dms.request

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage
import java.io.InputStream

/**
 * @author iPotato
 * @date 2019/11/3
 */
class MyHttpInputMessage(
        @get:JvmName("getHeaders_") val headers: HttpHeaders,
        @get:JvmName("getBody_") val body: InputStream
) : HttpInputMessage {
    override fun getHeaders() = headers
    override fun getBody() = body
}