package com.monkeydp.daios.dms.curd.service.contract

import com.monkeydp.daios.dms.sdk.entity.Entity

/**
 * @author iPotato
 * @date 2019/10/22
 */
interface CurdService<E : Entity> {
    
    fun save(entity: E): E
    
    fun findById(id: Long): E
    
    fun findAll(): List<E>
    
    fun delete(entity: E)
    
    fun deleteById(id: Long)
}