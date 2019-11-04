package com.monkeydp.daios.dms.sdk.metadata.node.ctx

import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
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
@NeedDatasource
data class NodeLoadCtxForm(
        @ApiModelProperty(required = true, example = CP_ID)
        val cpId: Long = INVALID_ID,
        @ApiModelProperty(required = true, example = NODE_INFO_PATH)
        val path: NodePath
) {
    fun toInner(conn: Conn<*>) = NodeLoadCtx(conn, path)
}