package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.conn.ConnProfile
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/20
 */
abstract class AbstractConnNode(
        override val cp: ConnProfile,
        defId: Int,
        target: Target<*>,
        icon: Icon<*>
) : ConnNode, AbstractNode(defId, target, cp.form.connName, icon)