package com.monkeydp.daios.dms.component

import com.monkeydp.daios.dms.sdk.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Component
class UserSession {
    @Autowired
    private lateinit var session: HttpSession
    
    fun getUserId() = getUser().id
    
    /**
     * TODO
     */
    fun getUser() = User.mockUser
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> getAttribute(key: Key) = session.getAttribute(key.name) as T
    
    enum class Key {
        USER
    }
}