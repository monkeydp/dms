package com.monkeydp.daios.dms.sdk.entity

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author iPotato
 * @date 2019/10/22
 */
@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue
    val id: Long = -1
}