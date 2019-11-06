package com.monkeydp.daios.dms.sdk.metadata.menu.ctx

import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuPath
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.sdk.mocker.MenuJsonMocker.MENU_PATH
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_PATH
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/1
 */
@ApiModel
@NeedDatasource
data class NodeMenuLoadCtxForm(
        @ApiModelProperty(required = true, example = CP_ID)
        val cpId: Long = IdHelper.INVALID_ID,
        @ApiModelProperty(required = true, example = NODE_PATH)
        val nodePath: NodePath,
        @ApiModelProperty(required = true, example = MENU_PATH)
        val menuPath: MenuPath = MenuPath()
) {
    fun toInner() = NodeMenuLoadCtx(nodePath, menuPath)
}