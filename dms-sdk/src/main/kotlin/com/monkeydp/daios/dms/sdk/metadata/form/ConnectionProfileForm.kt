package com.monkeydp.daios.dms.sdk.metadata.form

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class ConnectionProfileForm(map: Map<String, String>) {
    val connectionName by map
    val host by map
    val port by map
    val username by map
    val password by map
}