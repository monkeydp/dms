package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.useful.UserInput

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractCpForm(
        override val connName: String,
        override val host: String,
        override val port: String,
        override val username: String,
        override val password: String
) : CpForm