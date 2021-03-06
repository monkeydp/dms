package com.monkeydp.daios.dms.sdk.ui.icon

/**
 * @author iPotato
 * @date 2019/10/27
 */
@SdkIcon
enum class GlobalIcon(
        override val namex: String,
        override val color: String
) : Icon<GlobalIcon> {
    EMPTY_ICON("empty icon", ""),
    
    CONN_ICON("conn icon", ""),
    DB_ICON("db icon", ""),
    TABLE_ICON("table icon", ""),
    VIEW_ICON("view icon", ""),
    
    GROUP_ICON("default group icon", ""),
    DB_GROUP_ICON("db group icon", ""),
    TABLE_GROUP_ICON("table group icon", ""),
    VIEW_GROUP_ICON("view group icon", "");
    
    companion object {
        const val SUFFIX = "_ICON"
        const val GROUP_SUFFIX = "_GROUP$SUFFIX"
        fun appendSuffix(str: String, suffix: String = SUFFIX) = "$str$suffix"
    }
}