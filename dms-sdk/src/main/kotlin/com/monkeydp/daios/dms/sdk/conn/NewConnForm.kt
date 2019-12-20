package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.received.form.ReceivedForm

/**
 * @author iPotato
 * @date 2019/10/26
 */
interface NewConnForm : ReceivedForm {
    val connName: String
    val host: String
    val port: String
    val username: String
    val password: String
}