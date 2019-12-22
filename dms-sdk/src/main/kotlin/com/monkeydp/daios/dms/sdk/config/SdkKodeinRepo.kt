package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.tools.ext.kodein.component.abstr.AbstractKodeinRepo

/**
 * @author iPotato
 * @date 2019/12/22
 */
internal object SdkKodeinRepo : AbstractKodeinRepo() {
    override val annotReflections = reflections(
            packageNames = listOf(PackageName.sdk)
    )
    override val compReflections = reflections(
            packageNames = listOf(PackageName.sdk)
    )
}