package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.entity.Entity
import com.monkeydp.daios.dms.sdk.useful.UserInput


/**
 * @author iPotato
 * @date 2019/12/3
 */
interface ConnProfile : Entity {
    var userId: Long
    var datasource: Datasource
    var dsVersionId: String
    val dsVersion: DsVersion<*>
    var userInput: UserInput
    val form: NewConnForm
    fun copy(
            userId: Long = this.userId,
            datasource: Datasource = this.datasource,
            dsVersionId: String = this.dsVersionId,
            userInput: UserInput = this.userInput
    ): ConnProfile
}