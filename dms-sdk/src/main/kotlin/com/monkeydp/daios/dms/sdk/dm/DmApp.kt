package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.ext.getDatasourceByClassname
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodein
import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.tools.ext.logger.getLogger

/**
 * @author iPotato
 * @date 2019/12/4
 */
interface DmApp

abstract class AbstractDmApp(private val dmKodein: DmKodein) : DmApp {
    
    companion object {
        val log = getLogger()
    }
    
    init {
        dmKodeinRepo.putDmKodein(getDatasourceByClassname(), dmKodein)
    }
}