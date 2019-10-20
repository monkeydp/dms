package com.monkeydp.daios.dms.sdk.mock

import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource.MYSQL
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
object MockFactory {

    private fun randomId(): Long {
        return RandomUtil.randomInt(1, 1000).toLong()
    }

    private val mockMysql57Cp = ConnectionProfile(
            id = randomId(),
            datasource = MYSQL,
            dbVersionId = "5.7",
            dbDriverName = "com.mysql.jdbc.Driver"
    )

    private val mockMysql80Cp = ConnectionProfile(
            id = randomId(),
            datasource = MYSQL,
            dbVersionId = "8.0",
            dbDriverName = "com.mysql.cj.jdbc.Driver"
    )

    val mockConnectionProfile = mockMysql57Cp
}