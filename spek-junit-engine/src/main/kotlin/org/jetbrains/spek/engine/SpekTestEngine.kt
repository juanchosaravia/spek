package org.jetbrains.spek.engine

import org.jetbrains.spek.Spek
import org.junit.gen5.commons.util.ReflectionUtils
import org.junit.gen5.engine.EngineDiscoveryRequest
import org.junit.gen5.engine.ExecutionRequest
import org.junit.gen5.engine.TestDescriptor
import org.junit.gen5.engine.UniqueId
import org.junit.gen5.engine.discovery.ClassSelector
import org.junit.gen5.engine.discovery.ClasspathSelector
import org.junit.gen5.engine.discovery.PackageSelector
import org.junit.gen5.engine.support.descriptor.EngineDescriptor
import org.junit.gen5.engine.support.hierarchical.HierarchicalTestEngine
import kotlin.reflect.primaryConstructor

/**
 * @author Ranie Jade Ramiso
 */
class SpekTestEngine: HierarchicalTestEngine<SpekExecutionContext>() {
    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor {
        val engineDescriptor = SpekEngineDescriptor(uniqueId)
        resolveSpecs(discoveryRequest, engineDescriptor)
        return engineDescriptor
    }

    override fun getId(): String = "spek"

    override fun createExecutionContext(request: ExecutionRequest) = SpekExecutionContext()

    private fun resolveSpecs(discoveryRequest: EngineDiscoveryRequest, engineDescriptor: EngineDescriptor) {
        val isSpekClass = java.util.function.Predicate<Class<*>> { Spek::class.java.isAssignableFrom(it) }
        discoveryRequest.getSelectorsByType(ClasspathSelector::class.java).forEach {
            ReflectionUtils.findAllClassesInClasspathRoot(it.classpathRoot, isSpekClass).forEach {
                resolveSpec(engineDescriptor, it)
            }
        }

        discoveryRequest.getSelectorsByType(PackageSelector::class.java).forEach {
            ReflectionUtils.findAllClassesInPackage(it.packageName, isSpekClass).forEach {
                resolveSpec(engineDescriptor, it)
            }
        }

        discoveryRequest.getSelectorsByType(ClassSelector::class.java).forEach {
            if (isSpekClass.test(it.javaClass)) {
                resolveSpec(engineDescriptor, it.javaClass)
            }
        }
    }

    private fun resolveSpec(engineDescriptor: EngineDescriptor, klass: Class<*>) {
        val instance = klass.kotlin.primaryConstructor!!.call()
    }
}
