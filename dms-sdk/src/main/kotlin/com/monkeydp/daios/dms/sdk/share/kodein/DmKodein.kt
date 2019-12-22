package com.monkeydp.daios.dms.sdk.share.kodein

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
    }
}

private abstract class AbstractDmKodein(override val kodein: Kodein) : DmKodein

private class DmKodeinImpl(kodein: Kodein) : AbstractDmKodein(kodein)