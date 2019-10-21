package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
class Connection(
        val connectionProfileId: Long,
        private val logicConnection: LogicConnection
) {

    fun isValid(timeout: Int = 10): Boolean {
        return logicConnection.isValid(timeout)
    }

    fun close() {
        logicConnection.close()
    }

    fun isClosed(): Boolean {
        return logicConnection.isClosed()
    }
}