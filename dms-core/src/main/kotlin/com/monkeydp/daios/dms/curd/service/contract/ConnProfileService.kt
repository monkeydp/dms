package com.monkeydp.daios.dms.curd.service.contract

import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/22
 */
interface ConnProfileService : CurdService<ConnProfile> {
    fun findAllByUserId(userId: Long): List<ConnProfile>
}