package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.DB_GROUP_ICON

/**
 * @author iPotato
 * @date 2019/10/27
 */
class DbGroupNodeDef : AbstractGroupNodeDef() {
    override val info = super.info.copy(name = "Databases", icon = DB_GROUP_ICON)
}