package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.util.IdUtil
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
        open val id: Long = INVALID_ID
) {
    companion object {
        const val INVALID_ID = IdUtil.INVALID_ID
    }
    
    @JsonIgnore
    open fun isValid() = IdUtil.isValid(id)
}