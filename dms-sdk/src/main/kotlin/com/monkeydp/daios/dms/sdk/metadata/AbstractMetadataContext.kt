package com.monkeydp.daios.dms.sdk.metadata

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractMetadataContext : MetadataContext {
    override val globalInfo = MetadataContext.GlobalInfo()
}