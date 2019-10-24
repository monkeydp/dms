package com.monkeydp.daios.dms.connection

import com.monkeydp.daios.dms.connection.ConnectionWrapper.BelongsTo.TASK
import com.monkeydp.daios.dms.connection.ConnectionWrapper.BelongsTo.USER
import com.monkeydp.daios.dms.sdk.connection.Connection
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnectionWrapper(val connection: Connection) : AutoCloseable {
    
    // TODO auto increment id
    val connId = RandomUtil.randomId()
    
    val cpId = connection.cpId
    
    val belongsTo: BelongsTo = USER
    
    override fun close() {
        connection.close()
    }
    
    fun belongsToUser() = belongsTo == USER
    fun belongsToTask() = belongsTo == TASK
    
    enum class BelongsTo {
        /**
         * A user can only have one active connection
         */
        USER,
        /**
         * One user can have many tasks, every task corresponds to one active connection
         */
        TASK
    }
}