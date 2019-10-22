package com.monkeydp.daios.dms.curd.repository

import com.monkeydp.daios.dms.sdk.model.AbstractModel
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author iPotato
 * @date 2019/10/22
 */
interface CurdRepo<M : AbstractModel> : JpaRepository<M, Long>