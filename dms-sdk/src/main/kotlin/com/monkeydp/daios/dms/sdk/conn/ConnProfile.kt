package com.monkeydp.daios.dms.sdk.conn

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.SdkImplRegistry
import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import com.monkeydp.daios.dms.sdk.entity.User
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_USER_INPUT
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.tools.ext.convertTo
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

/**
 * @author iPotato
 * @date 2019/10/6
 */
@Entity
@ApiModel
data class ConnProfile(
        override val id: Long = INVALID_ID,
        
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
        
        /**
         * @see DsDriver
         */
        @Column(nullable = false)
        @JsonIgnore
        @ApiModelProperty(hidden = true)
        val dsDriverClassname: String = "",
        
        @Column(nullable = false, length = 1024)
        @Convert(converter = UserInput.StringConverter::class)
        @ApiModelProperty(
                value = "parameters entered by the user",
                required = true,
                example = CP_USER_INPUT
        )
        val userInput: UserInput
) : AbstractEntity(id) {
    
    val dsVersion: DsVersion<*>
        @JsonIgnore
        get() = SdkImplRegistry.getDsVersion(datasource, dsVersionId)
    
    
    val form: NewConnForm
        @JsonIgnore
        get() {
            val newConnFormClass = SdkImplRegistry.getNewConnFormClass(datasource)
            return userInput.convertTo(newConnFormClass)
        }
    
    @JsonIgnore
    override fun isValid() = super.isValid() && dsDriverClassname.isNotBlank()
}