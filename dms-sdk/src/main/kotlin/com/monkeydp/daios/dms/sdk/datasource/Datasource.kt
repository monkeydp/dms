package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.tools.ierror

/**
 * @author iPotato
 * @date 2019/10/15
 */
enum class Datasource {
    
    MYSQL;
    
    private val asPrefix = "${name}_"
    
    /**
     * Weather current datasource name is prefix of string
     */
    private fun isPrefixOf(string: String) = string.startsWith(asPrefix)
    
    fun checkPrefix(string: String) {
        if (!isPrefixOf(string)) throw ierror("${asPrefix} is not prefix of ${string}")
    }
    
    enum class DsVersion(
            val datasource: Datasource,
            val id: String,
            val description: String
    ) {
        MYSQL_5_7(MYSQL, "5.7", "MySQL 5.7"),
        MYSQL_8_0(MYSQL, "8.0", "MySQL 8.0"),
    }
}