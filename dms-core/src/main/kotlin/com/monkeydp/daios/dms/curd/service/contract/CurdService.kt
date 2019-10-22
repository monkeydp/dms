package com.monkeydp.daios.dms.curd.service.contract

import com.monkeydp.daios.dms.sdk.model.AbstractModel

/**
 * @author iPotato
 * @date 2019/10/22
 */
interface CurdService<M : AbstractModel> {
    fun save(model: M): M
}