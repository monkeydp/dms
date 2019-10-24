package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
class Connection(
        val cpId: Long,
        private val logicConnection: LogicConnection
) : AutoCloseable {
    
    fun isValid(timeout: Int = 10): Boolean {
        return logicConnection.isValid(timeout)
    }
    
    override fun close() {
        logicConnection.close()
    }
    
    fun isClosed(): Boolean {
        return logicConnection.isClosed()
    }
}