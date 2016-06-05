package org.jetbrains.spek.engine

import org.jetbrains.spek.engine.scope.Scope
import org.junit.gen5.engine.support.hierarchical.EngineExecutionContext
import java.util.*

/**
 * @author Ranie Jade Ramiso
 */
class SpekExecutionContext: EngineExecutionContext {
    private val bindings = LinkedHashSet<ExecutionAware>()

    fun bind(executionAware: ExecutionAware) {
        bindings.add(executionAware)
    }

    fun before(scope: Scope) {
        if (scope is Scope.Example) {
            bindings.forEach { it.beforeExample(scope) }
        }
    }
    fun after(scope: Scope) {
        if (scope is Scope.Example) {
            bindings.forEach { it.afterExample(scope) }
        }
    }
}
