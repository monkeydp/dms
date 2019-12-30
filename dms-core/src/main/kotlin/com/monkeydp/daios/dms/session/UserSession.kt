package com.monkeydp.daios.dms.session

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
    
    val userId: Long get() = user.id
    
    /**
     * TODO
     */
    val user: User get() = User.mockUser
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> getAttribute(key: Key) = session.getAttribute(key.name) as T
    
    enum class Key {
        USER
    }
}