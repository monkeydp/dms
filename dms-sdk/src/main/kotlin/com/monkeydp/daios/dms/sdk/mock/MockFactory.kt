package com.monkeydp.daios.dms.sdk.mock

import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource.MYSQL
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
object MockFactory {

    private val mockMysql57Cp = ConnectionProfile(
            id = RandomUtil.randomId(),
            datasource = MYSQL,
            dbVersionId = "5.7",
            dbDriverName = "com.mysql.jdbc.Driver",
            userInput = UserInput(mapOf(
                    "host" to "127.0.0.1",
                    "port" to "3306",
                    "username" to "root",
                    "password" to ""
            ))
    )

    private val mockMysql80Cp = ConnectionProfile(
            id = RandomUtil.randomId(),
            datasource = MYSQL,
            dbVersionId = "8.0",
            dbDriverName = "com.mysql.cj.jdbc.Driver",
            userInput = UserInput(mapOf(
                    "host" to "127.0.0.1",
                    "port" to "3307",
                    "username" to "root",
                    "password" to ""
            ))
    )

    val mockConnectionProfile = mockMysql57Cp
}