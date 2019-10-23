package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.CurdRepo
import com.monkeydp.daios.dms.curd.service.contract.CurdService
import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
internal abstract class CurdServiceImpl<entity : AbstractEntity, R : CurdRepo<entity>> : CurdService<entity> {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var repo: R

    override fun save(entity: entity): entity = repo.save(entity)

    override fun findById(id: Long): entity = repo.findById(id).get()

    override fun findAll(): List<entity> = repo.findAll()

    override fun delete(entity: entity) = repo.delete(entity)

    override fun deleteById(id: Long) = repo.deleteById(id)
}