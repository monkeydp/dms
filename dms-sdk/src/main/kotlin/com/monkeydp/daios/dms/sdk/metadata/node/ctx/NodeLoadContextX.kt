package com.monkeydp.daios.dms.sdk.metadata.node.ctx

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.node.info.NodeInfoPath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_INFO_PATH
import com.monkeydp.daios.dms.sdk.util.IdUtil.INVALID_ID
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/10/25
 */
@ApiModel
data class NodeLoadContextX(
        @ApiModelProperty(required = true, example = CP_ID)
        val cpId: Long = INVALID_ID,
        @ApiModelProperty(required = true, example = NODE_INFO_PATH)
        val path: NodeInfoPath
) {
    fun toInner(conn: Conn<*>) = NodeLoadContext(conn, path)
}