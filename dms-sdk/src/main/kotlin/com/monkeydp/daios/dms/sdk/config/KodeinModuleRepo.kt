package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.daios.dms.sdk.mocker.KodeinModulesMocker.Companion.mockDmsKodeinModule
import com.monkeydp.tools.ext.kodein.KodeinHelper.bindComps
import com.monkeydp.tools.ext.kotlin.singleton
import com.monkeydp.tools.ext.kotlin.toPropValuesX
import org.kodein.di.Kodein
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface KodeinModuleRepo {
    companion object {
        private var _dmsKodeinModule: Kodein.Module by Delegates.singleton(mockDmsKodeinModule)
        
        val dmsKodeinModule: Kodein.Module get() = _dmsKodeinModule
        
        val sdkKodeinModule = Kodein.Module("sdkKodeinModule") {
            bindComps(SdkKodeinCompRepo.comps)
        }
        
        fun setDmsKodeinModule(module: Kodein.Module) {
            _dmsKodeinModule = module
        }
        
        fun modules(): Array<Kodein.Module> = toPropValuesX<Kodein.Module>().toTypedArray()
    }
}