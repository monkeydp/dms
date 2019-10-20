package com.monkeydp.daios.dms.sdk.connection

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.DbDriver
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion
import com.monkeydp.daios.dms.sdk.useful.UserInput

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
        val dbVersionId: String,
        /**
         * @see DbDriver
         */
        val dbDriverName: String,
        val userInput: UserInput
)