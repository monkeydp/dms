package com.monkeydp.daios.dms.sdk.dm

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {

    fun implClassNames(): ImplClassNames

    /**
     * Implementation class names of all sdk contract
     */
    interface ImplClassNames {
        fun connectionFactory(): String?
    }
}