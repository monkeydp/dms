package com.monkeydp.daios.dms.connection

import com.monkeydp.daios.dms.sdk.connection.Connection
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
class ConnectionWrapper(val connection: Connection) {
    /**
     * Connection id
     */
    val id: Long

    init {
        // TODO auto increment id
        id = RandomUtil.randomInt(1, 1000).toLong()
    }
}