package com.monkeydp.daios.dms.sdk.conn

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import com.monkeydp.daios.dms.sdk.entity.User
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_USER_INPUT
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.ext.convertTo
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*
import javax.persistence.EnumType.STRING
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/6
 */
@Entity
@ApiModel
data class ConnProfile(
        @JsonIgnore
        @Column(nullable = false)
        val userId: Long = User.mockUser.id,
        
        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(required = true, example = DATASOURCE)
        val datasource: Datasource,
        
        @Column(nullable = false)
        @ApiModelProperty(value = "datasource version id", required = true, example = DS_VERSION_ID)
        val dsVersionId: String,
        
        @Column(nullable = false, length = 1024)
        @Convert(converter = UserInput.StringConverter::class)
        @ApiModelProperty(
                value = "parameters entered by the user",
                required = true,
                example = CP_USER_INPUT
        )
        val userInput: UserInput
) : AbstractEntity() {
    
    val dsVersion: DsVersion<*>
        @JsonIgnore
        @Transient
        get() = DmHelper.findDsVersion(datasource, dsVersionId)
    
    
    val form: NewConnForm
        @JsonIgnore
        @Transient
        get() {
            val kClass = DmHelper.findImpl<KClass<out NewConnForm>>(datasource)
            return userInput.convertTo(kClass)
        }
}