package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.daios.dms.sdk.util.IdUtil
import com.monkeydp.daios.dms.sdk.util.IdUtil.INVALID_ID
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

        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(required = true, example = "MYSQL")
        val datasource: Datasource,

        @Column(nullable = false)
        @ApiModelProperty(value = "datasource version id", required = true, example = "5.7")
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
                example = """{
                        "connName": "MySQL 5.7",
                        "host": "127.0.0.1",
                        "port": 3306,
                        "username": "root",
                        "password": ""
                }"""
        )
        val userInput: UserInput
) : AbstractEntity(id) {
    
    @JsonIgnore
    fun getDsVersion() = DmImplRegistry.getDsVersion(datasource, dsVersionId)
    
    val form: CpForm
        @JsonIgnore
        get() {
            val cpFormClass = DmImplRegistry.getCpFormClass(datasource)
            return userInput.convertTo(cpFormClass)
        }
    
    @JsonIgnore
    fun isValid() = IdUtil.isValid(id) && dsDriverClassname.isNotBlank()
}