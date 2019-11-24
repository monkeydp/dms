package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.conn.HasCpId
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/10/25
 */
@ApiModel
class NodeLoadingCtx(
        @ApiModelProperty(required = true, example = ConnJsonMocker.CP_ID)
        override val cpId: Long,
        @ApiModelProperty(required = true, example = NodeJsonMocker.NODE_PATH)
        val path: NodePath
) : HasCpId {
    constructor(path: NodePath) : this(IdHelper.INVALID_ID, path)
}