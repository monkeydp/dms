package com.monkeydp.daios.dms.sdk.conn

/**
 * @author iPotato
 * @date 2019/10/26
 */
interface NewConnForm {
    val connName: String
    val host: String
    val port: String
    val username: String
    val password: String
}