package com.monkeydp.daios.dms.sdk.metadata.menu.ctx

import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfoPath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_INFO_PATH
import com.monkeydp.daios.dms.sdk.util.IdUtil
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/1
 */
data class NodeMenuLoadCtxForm(
        @ApiModelProperty(required = true, example = CP_ID)
        val cpId: Long = IdUtil.INVALID_ID,
        @ApiModelProperty(required = true, example = NODE_INFO_PATH)
        val path: NodeInfoPath
) {
    fun toInner() = NodeMenuLoadCtx(path)
}