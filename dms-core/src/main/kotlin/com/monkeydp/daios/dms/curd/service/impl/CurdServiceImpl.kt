package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.CurdRepo
import com.monkeydp.daios.dms.curd.service.contract.CurdService
import com.monkeydp.daios.dms.sdk.contract.AbstractModel
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author iPotato
 * @date 2019/10/22
 */
internal abstract class CurdServiceImpl<M : AbstractModel, R : CurdRepo<M>> : CurdService<M> {
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var repo: R

    override fun save(model: M) {
        repo.save(model)
    }
}