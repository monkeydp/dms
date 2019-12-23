package com.monkeydp.daios.dms.sdk.metadata.form

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface Form {
    val items: List<FormItem>
    companion object{
        operator fun invoke(items: List<FormItem>): Form = StdFrom(items)
    }
}

abstract class AbstractFrom(
        override val items: List<FormItem>
) : Form

private class StdFrom(items: List<FormItem>) : AbstractFrom(items)