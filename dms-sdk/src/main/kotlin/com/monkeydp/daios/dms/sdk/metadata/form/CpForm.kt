package com.monkeydp.daios.dms.sdk.metadata.form

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class CpForm(map: Map<String, String>) {
    val connName by map
    val host by map
    val port by map
    val username by map
    val password by map
}