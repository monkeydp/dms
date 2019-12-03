package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

/**
 * @author iPotato
 * @date 2019/12/3
 */
@MappedSuperclass
interface Entity {
    companion object {
        const val INVALID_ID = IdHelper.INVALID_ID
    }
    
    val id: Long
    
    @JsonIgnore
    @Transient
    fun isValid() = IdHelper.isValid(id)
}