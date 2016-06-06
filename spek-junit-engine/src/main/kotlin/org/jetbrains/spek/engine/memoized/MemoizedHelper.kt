package org.jetbrains.spek.engine.memoized

import org.jetbrains.spek.engine.ExecutionAware
import org.jetbrains.spek.engine.scope.Scope
import kotlin.reflect.KProperty

/**
 * @author Ranie Jade Ramiso
 */
open class MemoizedHelper<T>(val factory: () -> T): ExecutionAware {
    private var instance: T? = null

    fun get(): T {
        if (instance == null) {
            instance = factory()
        }
        return instance!!
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()

    override fun beforeExample(example: Scope.Example) {
        instance = null
    }

    override fun afterExample(example: Scope.Example) { }

}
