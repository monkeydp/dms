package com.monkeydp.daios.dms.sdk.business

/**
 * @author iPotato
 * @date 2019/10/25
 */
enum class ErrorInfo(
        override val code: String,
        override val message: String
) : BusinessInfo<ErrorInfo> {
    
    ERR("-1", "错误");
    
    companion object {
        val instance = this
    }
}