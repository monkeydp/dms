package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource

/**
 * Datasource module
 *
 * @author iPotato
 * @date 2019/10/8
 */
interface Dm {

    fun datasource(): Datasource
    fun implClassNames(): ImplClassNames

    /**
     * Implementation class names of all sdk required contract
     */
    interface ImplClassNames {
        fun connectionFactory(): String?
    }
}