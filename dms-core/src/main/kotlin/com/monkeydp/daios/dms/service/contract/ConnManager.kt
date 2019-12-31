package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/25
 */
interface ConnManager {
    fun activateCp(cp: ConnProfile)
    fun inactivateCp(cpId: Long)
    fun inactivateAllCp()
    fun updateActiveCp(cp: ConnProfile)
    fun getActiveCp(cpId: Long): ConnProfile
    fun getActiveCpOrNull(cpId: Long): ConnProfile?
    
    fun activateCw(cw: ConnWrapper): ConnManager
    fun inactivateCw(cpId: Long, connId: Long)
    fun inactivateUserCw(cpId: Long)
    fun inactivateAllCw(cpId: Long)
    fun getActiveCw(cpId: Long, connId: Long): ConnWrapper
    fun getActiveCwOrNull(cpId: Long, connId: Long): ConnWrapper?
    fun getActiveUserCw(cpId: Long): ConnWrapper
    fun getActiveUserCwOrNull(cpId: Long): ConnWrapper?
}