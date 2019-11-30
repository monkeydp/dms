package com.monkeydp.daios.dms.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author iPotato
 * @date 2019/11/30
 */
class DmsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello") {
            it.group = "dms"
            it.doLast {
                println("Thanks for using dms plugin!")
            }
        }
    }
}