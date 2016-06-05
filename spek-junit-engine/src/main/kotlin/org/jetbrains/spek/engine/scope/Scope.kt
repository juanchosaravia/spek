package org.jetbrains.spek.engine.scope

import org.jetbrains.spek.dsl.Pending
import org.jetbrains.spek.engine.SpekExecutionContext
import org.jetbrains.spek.engine.subject.SubjectImpl
import org.junit.gen5.engine.UniqueId
import org.junit.gen5.engine.support.descriptor.AbstractTestDescriptor
import org.junit.gen5.engine.support.hierarchical.Node

/**
 * @author Ranie Jade Ramiso
 */
sealed class Scope(uniqueId: UniqueId, val pending: Pending, val fixtures: Fixtures)
    : AbstractTestDescriptor(uniqueId), Node<SpekExecutionContext> {
    class ExampleGroup(uniqueId: UniqueId, pending: Pending, fixtures: Fixtures, val subject: SubjectImpl<*>)
        : Scope(uniqueId, pending, fixtures) {
        override fun isTest() = false
        override fun isContainer() = true
        override fun prepare(context: SpekExecutionContext?): SpekExecutionContext? {
            return super.prepare(context).apply {
                bind(subject)
            }
        }
    }

    class Example(uniqueId: UniqueId, pending: Pending, fixtures: Fixtures, val body: () -> Unit)
        : Scope(uniqueId, pending, fixtures) {
        override fun isTest() = true
        override fun isContainer() = false
        override fun isLeaf() = true
        override fun execute(context: SpekExecutionContext): SpekExecutionContext {
            body.invoke()
            return context
        }
    }

    override fun getDisplayName() = uniqueId.segments.last().value

    override fun shouldBeSkipped(context: SpekExecutionContext): Node.SkipResult {
        return when(pending) {
            is Pending.Yes -> Node.SkipResult.skip(pending.reason)
            else -> Node.SkipResult.doNotSkip()
        }
    }

    override fun before(context: SpekExecutionContext): SpekExecutionContext {
        context.before(this)
        invokeAllBeforeEach(this)
        return context
    }

    override fun after(context: SpekExecutionContext) {
        invokeAllAfterEach(this)
        context.after(this)
    }

    private fun invokeAllBeforeEach(scope: Scope) {
        if (scope.parent.isPresent) {
            invokeAllBeforeEach(scope.parent.get() as Scope)
        }
        scope.fixtures.beforeEach?.invoke()
    }

    private fun invokeAllAfterEach(scope: Scope) {
        scope.fixtures.afterEach?.invoke()
        if (scope.parent.isPresent) {
            invokeAllAfterEach(scope.parent.get() as Scope)
        }
    }
}
