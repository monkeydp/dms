package com.monkeydp.daios.dms.sdk.metadata.node.def

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.TABLE_GROUP_ICON

/**
 * @author iPotato
 * @date 2019/10/27
 */
class TableGroupNodeDef : AbstractGroupNodeDef() {
    override val info = super.info.copy(name = "Tables", icon = TABLE_GROUP_ICON)
}