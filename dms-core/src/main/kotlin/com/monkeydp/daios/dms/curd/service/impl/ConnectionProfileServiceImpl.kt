package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.ConnectionProfileRepo
import com.monkeydp.daios.dms.curd.service.contract.ConnectionProfileService
import com.monkeydp.daios.dms.sdk.entity.ConnectionProfile
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Service
internal class ConnectionProfileServiceImpl :
        ConnectionProfileService,
        CurdServiceImpl<ConnectionProfile, ConnectionProfileRepo>()