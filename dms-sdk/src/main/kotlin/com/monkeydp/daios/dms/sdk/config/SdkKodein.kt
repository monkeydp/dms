package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.daios.dms.sdk.share.kodein.sdkKodeinModule
import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/15
 */
internal val kodein = Kodein {
    import(sdkKodeinModule)
}