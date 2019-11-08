package com.monkeydp.daios.dms.sdk.annot

import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Weather json deserializer needs datasource
 * @author iPotato
 * @date 2019/11/3
 */
@Target(CLASS)
annotation class NeedDatasource(val cpIdName: String = "cpId") {
}