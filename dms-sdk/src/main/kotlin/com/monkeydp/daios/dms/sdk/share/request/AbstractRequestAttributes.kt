package com.monkeydp.daios.dms.sdk.share.request

import com.monkeydp.tools.ext.kotlin.toPropMap

/**
 * @author iPotato
 * @date 2019/12/15
 */

internal abstract class AbstractRequestAttributes : RequestAttributes {
    
    private val _attrs = mutableMapOf<String, Any?>()
    override val attrs get() = _attrs.toMap()
    
    constructor(any: Any? = null) {
        if (any == null) return
        setAttributes(any.toPropMap({ it.name }))
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getAttribute(name: String) = _attrs.getValue(name) as T
    
    override fun setAttribute(name: String, value: Any?) {
        _attrs[name] = value
    }
    
    override fun setAttributes(map: Map<String, Any?>) {
        _attrs.putAll(map)
    }
}