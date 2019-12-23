package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.tools.ext.kodein.KodeinHelper.bindComps
import com.monkeydp.tools.ext.kodein.component.contract.KodeinCompRepo
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
                    importAll(*kodeinModules)
                    bindComps(repo.comps)
                }
    }
}

private abstract class AbstractDmKodein(override val kodein: Kodein) : DmKodein

private class DmKodeinImpl(kodein: Kodein) : AbstractDmKodein(kodein)