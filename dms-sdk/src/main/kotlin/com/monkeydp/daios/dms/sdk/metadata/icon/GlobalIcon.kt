package com.monkeydp.daios.dms.sdk.metadata.icon

/**
 * @author iPotato
 * @date 2019/10/27
 */
enum class GlobalIcon(
        override val namex: String,
        override val color: String
) : Icon<GlobalIcon> {
    EMPTY_ICON("empty_icon", ""),
    
    CONN_ICON("conn_icon", ""),
    DB_ICON("db_icon", ""),
    TABLE_ICON("table_icon", ""),
    VIEW_ICON("view_icon", ""),
    
    GROUP_ICON("default_group_icon", ""),
    DB_GROUP_ICON("db_group_icon", ""),
    TABLE_GROUP_ICON("table_group_icon", ""),
    VIEW_GROUP_ICON("view_group_icon", ""),
}