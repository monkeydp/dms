package com.monkeydp.daios.dms.sdk.metadata.form

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractCpForm(
        map: Map<String, String>
) : CpForm {
    override val connName by map
    override val host by map
    override val port by map
    override val username by map
    override val password by map
}