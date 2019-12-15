package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.daios.dms.sdk.share.request.RequestAttributes
import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * @see RequestAttributes.conn annotated controller fun will auto assign `conn`
 * @author iPotato
 * @date 2019/11/29
 */
@Target(FUNCTION)
annotation class ConnRequired