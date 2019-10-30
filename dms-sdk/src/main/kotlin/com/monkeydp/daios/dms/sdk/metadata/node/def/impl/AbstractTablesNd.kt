package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.node.def.TablesNd

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractTablesNd(name: String = "Tables") : TablesNd, AbstractGroupNd() {
    override val info = super.info.copy(name = name, icon = GlobalIcon.TABLE_GROUP_ICON)
}