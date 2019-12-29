package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.conn.HasCpId
import com.monkeydp.daios.dms.sdk.helper.IdHelper.INVALID_ID
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.jetbrains.annotations.TestOnly

/**
 * @author iPotato
 * @date 2019/11/1
 */
@ApiModel
data class MenuLoadingCtx(
        @ApiModelProperty(required = true, example = CP_ID)
        override val cpId: Long,
        @ApiModelProperty(required = true, example = "1")
        val menuDefId: Int
) : HasCpId {
    @TestOnly
    constructor(
            menuDefId: Int
    ) : this(INVALID_ID, menuDefId)
}