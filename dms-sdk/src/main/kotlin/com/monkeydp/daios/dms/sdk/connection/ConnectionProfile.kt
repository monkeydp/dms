package com.monkeydp.daios.dms.sdk.connection

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.DbDriver
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion
import com.monkeydp.daios.dms.sdk.useful.UserInput
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

/**
 * @author iPotato
 * @date 2019/10/6
 */
@Entity
@ApiModel
data class ConnectionProfile(

        @Id
        @GeneratedValue
        @JsonIgnore
        val id: Long,

        @Column(nullable = false)
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
        var dbDriverName: String = "",

        @Column(nullable = false)
        @Convert(converter = UserInput.StringConverter::class)
        @ApiModelProperty(value = "parameters entered by the user", required = true, example = """{
            "connectionName": "MySQL 5.7",
            "host": "127.0.0.1",
            "port": 3306,
            "username": "root",
            "password": ""
        }""")
        val userInput: UserInput
)