package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.connection.ConnectionWrapper
import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile

/**
 * @author iPotato
 * @date 2019/10/18
 */
interface DispatcherService {
    fun connectionWrapper(profile: ConnectionProfile): ConnectionWrapper
}