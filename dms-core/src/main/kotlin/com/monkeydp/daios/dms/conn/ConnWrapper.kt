package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.conn.ConnWrapper.BelongsTo.TASK
import com.monkeydp.daios.dms.conn.ConnWrapper.BelongsTo.USER
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnWrapper(val conn: Conn) : AutoCloseable {
    
    // TODO auto increment id
    val connId = RandomUtil.randomId()
    
    val cpId = conn.cpId
    
    val belongsTo: BelongsTo = USER
    
    override fun close() {
        conn.close()
    }
    
    fun belongsToUser() = belongsTo == USER
    fun belongsToTask() = belongsTo == TASK
    
    enum class BelongsTo {
        /**
         * A user can only have one active conn
         */
        USER,
        /**
         * One user can have many tasks, every task corresponds to one active conn
         */
        TASK
    }
}