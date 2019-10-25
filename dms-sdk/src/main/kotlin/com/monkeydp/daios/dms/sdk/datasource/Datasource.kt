package com.monkeydp.daios.dms.sdk.datasource

/**
 * @author iPotato
 * @date 2019/10/15
 */
enum class Datasource {
    
    MYSQL;
    
    enum class DsVersion(
            val datasource: Datasource,
            val id: String,
            val description: String
    ) {
        MYSQL_5_7(MYSQL, "5.7", "MySQL 5.7"),
        MYSQL_8_0(MYSQL, "8.0", "MySQL 8.0"),
    }
}