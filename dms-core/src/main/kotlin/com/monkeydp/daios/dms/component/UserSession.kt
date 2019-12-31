package com.monkeydp.daios.dms.component

import com.monkeydp.daios.dms.sdk.entity.User
import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/28
 */
@Component
class UserSession @Autowired constructor(
        val session: HttpSession
) {
    val userId: Long get() = user.id
    
    /**
     * TODO
     */
    val user: User get() = User.mockUser
    
    final inline fun <reified T> getAttribute(): T = session.getAttribute(Key.find<T>().name) as T
    
    final inline fun <reified T> getAttributeOrNull(): T? = session.getAttribute(Key.find<T>().name) as? T
    
    fun setAttribute(key: Key, any: Any?) = session.setAttribute(key.name, any)
    
    enum class Key(val kClass: KClass<*>) {
        USER(User::class),
        UI_CONTEXT_REPO(UiContextRepo::class);
        
        companion object {
            inline fun <reified T> find() = values().first { it.kClass == T::class }
        }
    }
}