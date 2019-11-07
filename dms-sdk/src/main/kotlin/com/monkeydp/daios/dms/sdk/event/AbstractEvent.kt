package com.monkeydp.daios.dms.sdk.event

/**
 * @author iPotato
 * @date 2019/11/6
 */
abstract class AbstractEvent(
        override val source: Any,
        override val notifyUi: Boolean = false
) : Event {
}