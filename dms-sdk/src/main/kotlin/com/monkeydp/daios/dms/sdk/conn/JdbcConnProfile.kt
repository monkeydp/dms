package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.useful.UserInput

/**
 * @author iPotato
 * @date 2019/12/3
 */
interface JdbcConnProfile : ConnProfile {
    var dsDriverClassname: String
    
    override fun copy(
            userId: Long,
            datasource: Datasource,
            dsVersionId: String,
            userInput: UserInput
    ): JdbcConnProfile = copy(userId, datasource, dsVersionId, userInput, this.dsDriverClassname)
    
    fun copy(
            userId: Long = this.userId,
            datasource: Datasource = this.datasource,
            dsVersionId: String = this.dsVersionId,
            userInput: UserInput = this.userInput,
            dsDriverClassname: String = this.dsDriverClassname
    ): JdbcConnProfile
}