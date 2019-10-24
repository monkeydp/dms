package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion
import com.monkeydp.daios.dms.sdk.useful.UserInput
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*
import javax.persistence.EnumType.STRING

/**
 * @author iPotato
 * @date 2019/10/6
 */
@Entity
@ApiModel
data class ConnectionProfile(
        override val id: Long = INVALID_ID,

        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(required = true, example = "MYSQL")
        val datasource: Datasource,

        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(value = "datasource version", required = true, example = "MYSQL_5_7")
        val dsVersion: DsVersion,

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
                        "connectionName": "MySQL 5.7",
                        "host": "127.0.0.1",
                        "port": 3306,
                        "username": "root",
                        "password": ""
                }"""
        )
        val userInput: UserInput
) : AbstractEntity(id)