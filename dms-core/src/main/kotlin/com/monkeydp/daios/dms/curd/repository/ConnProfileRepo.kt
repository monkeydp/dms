package com.monkeydp.daios.dms.curd.repository

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import org.springframework.stereotype.Repository

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Repository
interface ConnProfileRepo : CurdRepo<ConnProfile> {
    fun findAllByUserId(userId: Long): List<ConnProfile>
}