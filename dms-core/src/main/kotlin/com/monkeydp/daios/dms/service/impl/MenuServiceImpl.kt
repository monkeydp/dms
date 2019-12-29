package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.sdk.api.MenuApi
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl
import com.monkeydp.daios.dms.service.contract.MenuService
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class MenuServiceImpl : MenuService {
    
    private val api: MenuApi get() = dmKodeinRepo.findImpl()
    
    override fun loadMenu(menuDefId: Int) = api.loadMenu(menuDefId)
}