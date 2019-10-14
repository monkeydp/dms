package com.monkeydp.daios.dms.boot

import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener

/**
 * @author iPotato
 * @date 2019/10/14
 */
class StartedEventListener : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val moduleDeployer: ModuleDeployer = event.applicationContext.getBean("moduleDeployer") as ModuleDeployer
        moduleDeployer.deployAllModules()
    }
}