package com.monkeydp.daios.dms.sdk.conn

/**
 * @author iPotato
 * @date 2019/10/29
 */
abstract class AbstractConn<C>(
        override val cpId: Long,
        override val rawConn: C
) : Conn<C>