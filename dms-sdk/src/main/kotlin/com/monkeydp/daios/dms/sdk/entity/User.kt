package com.monkeydp.daios.dms.sdk.entity

import io.swagger.annotations.ApiModel
import javax.persistence.Entity

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Entity
@ApiModel
data class User(
        override val id: Long = INVALID_ID,
        val account: String,
        val password: String
) : AbstractEntity() {
    companion object {
        val mockUser = User(account = "<mock account>", password = "<mock password>")
    }
}