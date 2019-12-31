package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.config.KodeinModuleRepo
import com.monkeydp.tools.ext.kodein.KodeinHelper.bindComps
import com.monkeydp.tools.ext.kodein.component.KodeinCompRepo
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * @author iPotato
 * @date 2019/12/9
 */
interface DmKodein : KodeinAware {
    companion object {
        operator fun invoke(allowSilentOverride: Boolean = false, init: Kodein.MainBuilder.() -> Unit): DmKodein =
                DmKodeinImpl(Kodein(allowSilentOverride, init))
        
        operator fun invoke(repo: KodeinCompRepo): DmKodein =
                this(true) {
                    importAll(*KodeinModuleRepo.modules())
                    bindComps(repo.comps)
                }
    }
}

private abstract class AbstractDmKodein(override val kodein: Kodein) : DmKodein

private class DmKodeinImpl(kodein: Kodein) : AbstractDmKodein(kodein)