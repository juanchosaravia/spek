package org.jetbrains.spek.engine

import org.jetbrains.spek.engine.scope.Scope

/**
 * @author Ranie Jade Ramiso
 */
interface ExecutionAware {
    fun beforeExample(example: Scope.Example)
    fun afterExample(example: Scope.Example)
}
