package com.monkeydp.daios.dms.sdk.connection

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.DbDriver
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion
import com.monkeydp.daios.dms.sdk.useful.UserInput
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/10/6
 */
@ApiModel
data class ConnectionProfile(
        @JsonIgnore
        val id: Long,
        @ApiModelProperty(required = true, example = "MYSQL")
        val datasource: Datasource,
        /**
         * @see DbVersion
         */
        @ApiModelProperty(value = "database version id", required = true, example = "5.7")
        val dbVersionId: String,
        /**
         * @see DbDriver
         */
        @JsonIgnore
        @ApiModelProperty(hidden = true)
        val dbDriverName: String,
        @ApiModelProperty(value = "parameters entered by the user", required = true, example = """{
            "connectionName": "MySQL 5.7",
            "host": "127.0.0.1",
            "port": 3306,
            "username": "root",
            "password": ""
        }""")
        val userInput: UserInput
)