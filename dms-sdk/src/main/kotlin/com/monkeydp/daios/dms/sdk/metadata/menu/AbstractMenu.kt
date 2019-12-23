package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItem

/**
 * @author iPotato
 * @date 2019/12/20
 */
abstract class AbstractMenu(override val items: List<MenuItem> = emptyList()) : Menu