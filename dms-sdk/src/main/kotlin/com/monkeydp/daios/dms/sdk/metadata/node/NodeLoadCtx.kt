package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker
import com.monkeydp.tools.ext.notNullSingleton
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/10/25
 */
@ApiModel
@NeedDatasource
class NodeLoadCtx(
        @ApiModelProperty(required = true, example = ConnJsonMocker.CP_ID)
        val cpId: Long,
        @ApiModelProperty(required = true, example = NodeJsonMocker.NODE_PATH)
        val path: NodePath
) {
    var conn: Conn<*> by Delegates.notNullSingleton()
    
    constructor(conn: Conn<*>, path: NodePath) : this(IdHelper.INVALID_ID, path) {
        this.conn = conn
    }
}