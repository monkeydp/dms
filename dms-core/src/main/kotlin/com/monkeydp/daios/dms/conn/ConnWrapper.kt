package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.conn.BelongsTo.TASK
import com.monkeydp.daios.dms.conn.BelongsTo.USER
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.tools.function.ierror
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnWrapper(val conn: Conn<*>, private val belongsTo: BelongsTo = USER) : AutoCloseable {
    
    // TODO auto increment id
    val connId = RandomUtil.randomId()
    
    val cpId = conn.cpId
    
    override fun close() {
        conn.close()
    }
    
    fun belongsToUser() = belongsTo == USER
    fun belongsToTask() = belongsTo == TASK
    
    fun checkBelongsToUser() {
        if (!this.belongsToUser()) ierror("Conn must belongs to user!")
    }
    
    fun checkNotBelongsToUser() {
        if (this.belongsToUser()) ierror("Conn must not belongs to user!")
    }
}