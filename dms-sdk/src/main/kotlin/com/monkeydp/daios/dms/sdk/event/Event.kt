package com.monkeydp.daios.dms.sdk.event

/**
 * @author iPotato
 * @date 2019/11/6
 */
interface Event {
    /**
     * The object on which the Event initially occurred.
     */
    val source: Any
    /**
     * Notify ui or not, if true, the event would be send to ui
     */
    val notifyUi: Boolean
}