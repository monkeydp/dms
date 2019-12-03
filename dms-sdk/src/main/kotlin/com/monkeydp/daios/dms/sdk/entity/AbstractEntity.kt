package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

/**
 * @author iPotato
 * @date 2019/10/22
 */
@MappedSuperclass
abstract class AbstractEntity(
        @Id
        @GeneratedValue
        val id: Long = INVALID_ID
) {
    companion object {
        const val INVALID_ID = IdHelper.INVALID_ID
    }
    
    @JsonIgnore
    @Transient
    fun isValid() = IdHelper.isValid(id)
}