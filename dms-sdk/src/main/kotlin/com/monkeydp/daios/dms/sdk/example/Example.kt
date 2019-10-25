package com.monkeydp.daios.dms.sdk.example

import com.monkeydp.daios.dms.sdk.entity.AbstractEntity
import com.monkeydp.daios.dms.sdk.util.IdUtil
import com.monkeydp.tools.util.RandomUtil
import org.jetbrains.annotations.TestOnly
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Entity
data class Example @TestOnly constructor(
        override val id: Long = IdUtil.INVALID_ID,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        @Enumerated(STRING)
        val status: Status
) : AbstractEntity(id) {
    
    enum class Status {
        ENABLED,
        DISABLED;
        
        companion object {
            fun random(): Status {
                val values = values()
                return values[RandomUtil.nextInt(values.size)]
            }
        }
    }
}