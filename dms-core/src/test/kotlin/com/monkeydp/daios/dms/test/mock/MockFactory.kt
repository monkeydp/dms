package com.monkeydp.daios.dms.test.mock

import com.monkeydp.daios.dms.sdk.connection.ConnectionProfile
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.tools.util.RandomUtil

/**
 * @author iPotato
 * @date 2019/10/18
 */
object MockFactory {
    val mockConnectionProfile = ConnectionProfile(
            id = RandomUtil.randomInt(1, 1000).toLong(),
            datasource = Datasource.MYSQL,
            dbVersionId = "5.7"
    )
}