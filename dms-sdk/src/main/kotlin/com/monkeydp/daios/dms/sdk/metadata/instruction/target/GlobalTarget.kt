package com.monkeydp.daios.dms.sdk.metadata.instruction.target

/**
 * @author iPotato
 * @date 2019/10/25
 */
enum class GlobalTarget : Target<GlobalTarget> {
    CONN, DB, TABLE, VIEW,
    GROUP,
    QUERY
}