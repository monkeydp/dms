package com.monkeydp.daios.dms.conn

import com.monkeydp.daios.dms.conn.BelongsTo.TASK
import com.monkeydp.daios.dms.conn.BelongsTo.USER
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.tools.ext.ierror

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnWrapper(val conn: Conn<*>, private val belongsTo: BelongsTo = USER) : AutoCloseable {
    
    init {
        // TODO auto increment id
        conn.id = IdHelper.randomId()
    }
    
    val connId = conn.id
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