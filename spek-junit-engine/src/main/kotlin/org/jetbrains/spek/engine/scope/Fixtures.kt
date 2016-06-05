package org.jetbrains.spek.engine.scope

/**
 * @author Ranie Jade Ramiso
 */
class Fixtures(val beforeEach: (() -> Unit)? = null, val afterEach: (() -> Unit)? = null)
