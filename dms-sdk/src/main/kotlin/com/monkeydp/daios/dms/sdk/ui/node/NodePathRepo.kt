package com.monkeydp.daios.dms.sdk.ui.node

import com.monkeydp.tools.ext.kotlin.toPropValuesX

interface NodePathRepo {
    fun getPaths(): List<NodePath> = toPropValuesX<NodePath>()
}