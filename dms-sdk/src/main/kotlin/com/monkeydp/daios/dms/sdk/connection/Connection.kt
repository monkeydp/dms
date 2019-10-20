package com.monkeydp.daios.dms.sdk.connection

/**
 * @author iPotato
 * @date 2019/10/6
 */
class Connection(
        val connectionProfileId: Long,
        val physicalConnection: Any
) {
    /**
     * Is connection valid
     * TODO
     */
    fun isValid(): Boolean {
        return true
    }
}