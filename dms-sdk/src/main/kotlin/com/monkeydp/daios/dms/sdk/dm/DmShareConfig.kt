package com.monkeydp.daios.dms.sdk.dm

import java.io.File

class DmShareConfig(
        /**
         * Class loader for loading the dm
         */
        val classLoader: ClassLoader,
        /**
         * Dir where the dm is deployed
         */
        val deployDir: File,
        /**
         * Dir where the classes is deployed
         */
        val classesDir: File
)