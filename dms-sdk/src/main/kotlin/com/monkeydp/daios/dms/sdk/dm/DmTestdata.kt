package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.conn.ConnProfile

interface DmTestdata {
    val cps: List<ConnProfile>
}