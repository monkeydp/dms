package com.monkeydp.daios.dms.sdk.datasource

import com.monkeydp.tools.ext.ierror

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
}