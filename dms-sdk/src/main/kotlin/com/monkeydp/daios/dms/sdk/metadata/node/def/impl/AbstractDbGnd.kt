package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDbGnd : AbstractGnd() {
    override val info = super.info.copy(name = "Databases", icon = GlobalIcon.DB_GROUP_ICON)
}