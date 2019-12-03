package com.monkeydp.daios.dms.sdk.entity

import com.monkeydp.daios.dms.sdk.entity.Entity.Companion.INVALID_ID
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author iPotato
 * @date 2019/10/22
 */
@MappedSuperclass
abstract class AbstractEntity(
        @Id
        @GeneratedValue
        override val id: Long = INVALID_ID
) : Entity