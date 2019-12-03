package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.main.SdkApiContract

/**
 * @author iPotato
 * @date 2019/10/6
 */
@SdkApiContract
interface ConnApi<CP : ConnProfile> {
    /**
     * Dm can add custom variable to cp
     */
    fun fullCp(cp: CP) = cp
    
    /**
     * Get a conn
     */
    fun getConn(cp: CP): Conn<*>
}