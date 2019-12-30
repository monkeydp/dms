package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.tools.ext.kotlin.findAnnot

/**
 * @author iPotato
 * @date 2019/12/4
 */
interface DmApp

abstract class AbstractDmApp(private val dmKodein: DmKodein) : DmApp {
    init {
        val sdkDmApp = javaClass.kotlin.findAnnot<SdkDmApp>()
        dmKodeinRepo.putDmKodein(sdkDmApp.datasource, dmKodein)
    }
}