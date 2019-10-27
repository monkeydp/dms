package com.monkeydp.daios.dms.sdk.conn

/**
 * @author iPotato
 * @date 2019/10/6
 */
class Conn(
        val cpId: Long,
        private val logicConn: LogicConn
) : AutoCloseable {
    
    fun isValid(timeout: Int = 10): Boolean {
        return logicConn.isValid(timeout)
    }
    
    override fun close() {
        logicConn.close()
    }
    
    fun isClosed(): Boolean {
        return logicConn.isClosed()
    }
}