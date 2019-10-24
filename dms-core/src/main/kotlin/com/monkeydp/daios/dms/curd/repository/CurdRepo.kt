package com.monkeydp.daios.dms.curd.repository

import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author iPotato
 * @date 2019/10/22
 */
interface CurdRepo<E : AbstractEntity> : JpaRepository<E, Long>