package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.CurdRepo
import com.monkeydp.daios.dms.curd.service.contract.CurdService
import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
internal abstract class CurdServiceImpl<E : AbstractEntity, R : CurdRepo<E>> : CurdService<E> {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var repo: R
    
    override fun save(entity: E): E = repo.save(entity)
    
    override fun saveAll(entities: Iterable<E>) = repo.saveAll(entities)
    
    override fun findById(id: Long): E = repo.findById(id).get()
    
    override fun findAll(): List<E> = repo.findAll()
    
    override fun delete(entity: E) = repo.delete(entity)
    
    override fun deleteById(id: Long) = repo.deleteById(id)
}