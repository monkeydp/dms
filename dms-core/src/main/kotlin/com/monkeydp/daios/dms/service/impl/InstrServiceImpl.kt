package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.instruction.InstrParsingCtx
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.InstrService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Service
class InstrServiceImpl : InstrService {
    @Autowired
    private lateinit var registry: ModuleRegistry
    @Autowired
    private lateinit var connService: ConnService
    
    override fun parse(ctx: InstrParsingCtx) {
        val cp = connService.findCp(ctx.cpId)
        val bundle = registry.getBundle(cp)
        val conn = connService.findConn(cp.id)
        ctx.conn = conn
        bundle.apis.instrApi.parse(ctx)
    }
}