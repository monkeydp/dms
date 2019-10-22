package com.monkeydp.daios.dms.sdk.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

/**
 * @author iPotato
 * @date 2019/10/22
 */
@Entity
data class ExampleModel(
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        @Enumerated(STRING)
        val status: ExampleModelStatus
) : AbstractModel() {
    enum class ExampleModelStatus {
        ENABLED,
        DISABLED
    }
}