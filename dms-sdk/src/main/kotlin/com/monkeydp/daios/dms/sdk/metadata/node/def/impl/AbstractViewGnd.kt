package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.node.def.ViewGnd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractViewGnd(name: String = "Views") : ViewGnd, AbstractGnd() {
    override val info = super.info.copy(name = name, icon = GlobalIcon.VIEW_GROUP_ICON)
}