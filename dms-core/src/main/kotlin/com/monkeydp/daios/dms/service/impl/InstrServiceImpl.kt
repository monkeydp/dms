package com.monkeydp.daios.dms.service.impl

import com.monkeydp.daios.dms.module.ModuleRegistry
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtx
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.InstrParseCtxForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.ctx.NodeInstrParseCtxForm
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
    
    override fun parse(form: InstrParseCtxForm) {
        val cp = connService.findCp(form.cpId)
        val bundle = registry.getBundle(cp)
        var ctx: InstrParseCtx
        when (form) {
            is NodeInstrParseCtxForm -> {
                val conn = connService.findConn(cp.id)
                ctx = form.toInner(conn)
            }
            else                     -> ierror("Unknown instruction parsing context!")
        }
        bundle.apis.instrApi.parse(ctx)
    }
}