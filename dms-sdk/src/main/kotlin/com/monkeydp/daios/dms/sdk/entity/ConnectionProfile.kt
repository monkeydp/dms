package com.monkeydp.daios.dms.sdk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.DbDriver
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion
import com.monkeydp.daios.dms.sdk.useful.UserInput
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
data class ConnectionProfile(
        @Column(nullable = false)
        @Enumerated(STRING)
        @ApiModelProperty(required = true, example = "MYSQL")
        val datasource: Datasource,
        /**
         * @see DbVersion
         */
        @Column(nullable = false)
        @ApiModelProperty(value = "database version id", required = true, example = "5.7")
        val dbVersionId: String,

        /**
         * @see DbDriver
         */
        @Column(nullable = false)
        @JsonIgnore
        @ApiModelProperty(hidden = true)
        val dbDriverName: String = "",

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
) : AbstractEntity()