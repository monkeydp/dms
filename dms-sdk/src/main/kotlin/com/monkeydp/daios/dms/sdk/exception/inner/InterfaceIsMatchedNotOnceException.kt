package com.monkeydp.daios.dms.sdk.exception.inner

import com.monkeydp.tools.exception.inner.AbstractInnerException
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/9
 */
class InterfaceIsMatchedNotOnceException(
        kClass: KClass<*>,
        matchedInterfaces: Collection<KClass<*>>
) : AbstractInnerException(
        """ Interface is matched ${matchedInterfaces.size} times, not once!
            Class is: ${kClass.java}
            Following interfaces are matched:
            $matchedInterfaces
        """
)