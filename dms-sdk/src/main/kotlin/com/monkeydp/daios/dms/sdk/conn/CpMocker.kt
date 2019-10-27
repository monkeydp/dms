package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion.MYSQL_5_7
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion.MYSQL_8_0
import com.monkeydp.daios.dms.sdk.datasource.Datasource.MYSQL
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.useful.UserInput

/**
 * @author iPotato
 * @date 2019/10/18
 */
object CpMocker {
    
    val mysql57Cp = ConnProfile(
            datasource = MYSQL,
            dsVersion = MYSQL_5_7,
            dsDriverClassname = "com.mysql.jdbc.Driver",
            userInput = UserInput(mapOf(
                    "connName" to "MySQL 5.7",
                    "host" to "127.0.0.1",
                    "port" to "3306",
                    "username" to "root",
                    "password" to ""
            ))
    )
    
    val mysql80Cp = ConnProfile(
            datasource = MYSQL,
            dsVersion = MYSQL_8_0,
            dsDriverClassname = "com.mysql.cj.jdbc.Driver",
            userInput = UserInput(mapOf(
                    "connName" to "MySQL 8.0",
                    "host" to "127.0.0.1",
                    "port" to "3307",
                    "username" to "root",
                    "password" to ""
            ))
    )
    
    val cpMap = mutableMapOf<DsVersion, ConnProfile>(
            MYSQL_5_7 to mysql57Cp,
            MYSQL_8_0 to mysql80Cp
    )

    fun testCp() = cpMap[MYSQL_5_7]!!
}