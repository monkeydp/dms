package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.conn.ConnWrapper
import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/11/25
 */
interface ConnManager {
    fun activateCp(cp: ConnProfile): ConnManager
    fun inactivateCp(cpId: Long, ignoreNotFound: Boolean = false)
    fun inactivateAllCp()
    fun updateActiveCp(cp: ConnProfile, ignoreNotFound: Boolean = false): ConnManager
    fun getActiveCp(cpId: Long): ConnProfile
    fun getActiveCp(cpId: Long, ignoreNotFound: Boolean): ConnProfile?
    
    fun activateCw(cw: ConnWrapper): ConnManager
    fun inactivateCw(cpId: Long, connId: Long, ignoreNotFound: Boolean = false)
    fun inactivateUserCw(cpId: Long, ignoreNotFound: Boolean = false)
    fun inactivateAllCw(cpId: Long)
    fun getActiveCw(cpId: Long, connId: Long): ConnWrapper
    fun getActiveCw(cpId: Long, connId: Long, ignoreNotFound: Boolean): ConnWrapper?
    fun getActiveUserCw(cpId: Long, ignoreNotFound: Boolean): ConnWrapper?
}