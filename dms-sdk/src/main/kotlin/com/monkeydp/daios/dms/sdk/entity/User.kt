package com.monkeydp.daios.dms.sdk.entity

import javax.persistence.Entity

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Entity
data class User(
        val account: String,
        val password: String
) : AbstractEntity() {
    companion object {
        val mockUser = User(
                account = "<mock account>",
                password = "<mock password>"
        )
    }
}