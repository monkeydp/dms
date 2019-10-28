package com.monkeydp.daios.dms.curd.service.impl

import com.monkeydp.daios.dms.curd.repository.ConnProfileRepo
import com.monkeydp.daios.dms.curd.service.contract.ConnProfileService
import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Service
internal class ConnProfileServiceImpl :
        ConnProfileService,
        CurdServiceImpl<ConnProfile, ConnProfileRepo>() {
    override fun findAllByUserId(userId: Long) = repo.findAllByUserId(userId)
}