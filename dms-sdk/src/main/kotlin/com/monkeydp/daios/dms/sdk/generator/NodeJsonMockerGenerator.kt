package com.monkeydp.daios.dms.sdk.generator

import com.monkeydp.daios.dms.sdk.config.SdkDirpath
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File

/**
 * @author iPotato
 * @date 2019/11/30
 */
class NodeJsonMockerGenerator {
    fun generate() {
        val file = FileSpec.builder("com.monkeydp.daios.dms.sdk.mocker", "NodeJsonMocker")
                .addType(TypeSpec.objectBuilder("NodeJsonMocker")
                        .addProperty(PropertySpec.builder("NODE_PATH", String::class)
                                .initializer("%S", "<generated node path>")
                                .build())
                        .build())
                .build()
        
        file.writeTo(System.out)
        file.writeTo(File(SdkDirpath.kotlinGenerated))
    }
}