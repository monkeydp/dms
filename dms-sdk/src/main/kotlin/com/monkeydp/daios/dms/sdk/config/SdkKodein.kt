package com.monkeydp.daios.dms.sdk.config

import org.kodein.di.Kodein

/**
 * @author iPotato
 * @date 2019/12/15
 */
internal val kodein = Kodein {
    importAll(*KodeinModuleRepo.modules())
}