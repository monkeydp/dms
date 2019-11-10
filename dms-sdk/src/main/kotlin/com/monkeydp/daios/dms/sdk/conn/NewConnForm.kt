package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.main.SdkFormContract

/**
 * @author iPotato
 * @date 2019/10/26
 */
@SdkFormContract
interface NewConnForm {
    val connName: String
    val host: String
    val port: String
    val username: String
    val password: String
}