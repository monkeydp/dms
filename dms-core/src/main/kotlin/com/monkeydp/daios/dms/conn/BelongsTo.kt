package com.monkeydp.daios.dms.conn

import com.monkeydp.tools.ierror

enum class BelongsTo {
    /**
     * A user can only have one active conn
     */
    USER,
    /**
     * One user can have many tasks, every task corresponds to one active conn
     */
    TASK;
}