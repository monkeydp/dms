package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtx
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.NodeInstrParseCtx
import com.monkeydp.daios.dms.service.contract.ConnService
import com.monkeydp.daios.dms.service.contract.InstrService
import com.monkeydp.tools.ext.ierror
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
    
    override fun parse(ctx: InstrParseCtx) {
        val cp = connService.findCp(ctx.cpId)
        val bundle = registry.getBundle(cp)
        when (ctx) {
            is NodeInstrParseCtx -> {
                val conn = connService.findConn(cp.id)
                ctx.conn = conn
            }
            else -> ierror("Unknown instruction parsing context!")
        }
        bundle.apis.instrApi.parse(ctx)
    }
}