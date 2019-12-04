package com.monkeydp.daios.dms.boot

import com.monkeydp.daios.dms.module.ModuleDeployer
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.DmTestdataRegistry
import com.monkeydp.daios.dms.sdk.main.SdkImplRegistry
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DATASOURCE
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.DS_VERSION_ID
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener

/**
 * @author iPotato
 * @date 2019/10/14
 */
class StartedEventListener : ApplicationListener<ApplicationStartedEvent> {
    
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val moduleDeployer = event.applicationContext.getBean(ModuleDeployer::class.java)
        moduleDeployer.deployAllModules()
        DmTestdataRegistry.testDsVersion = SdkImplRegistry.findDsVersion(Datasource.valueOf(DATASOURCE), DS_VERSION_ID)
    }
}