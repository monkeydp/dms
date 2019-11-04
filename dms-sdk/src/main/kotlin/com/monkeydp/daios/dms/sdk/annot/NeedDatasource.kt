package com.monkeydp.daios.dms.sdk.annot

/**
 * Weather json deserializer needs datasource
 * @author iPotato
 * @date 2019/11/3
 */
@Target(AnnotationTarget.CLASS)
annotation class NeedDatasource(val cpIdName: String = "cpId") {
}