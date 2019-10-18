package com.monkeydp.daios.dms.sdk.connection

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion

/**
 * @author iPotato
 * @date 2019/10/6
 */
data class ConnectionProfile(
        val id: Long,
        val datasource: Datasource,
        /**
         * @see DbVersion
         */
        val dbVersionId: String
)