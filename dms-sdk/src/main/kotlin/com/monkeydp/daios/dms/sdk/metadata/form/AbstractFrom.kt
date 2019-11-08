package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.metadata.form.item.FormItem

/**
 * @author iPotato
 * @date 2019/11/8
 */
abstract class AbstractFrom(
        override val items: List<FormItem>
) : Form