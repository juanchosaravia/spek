package org.jetbrains.spek.dsl

import kotlin.reflect.KClass

/**
 * Creates an [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.describe(description: String, body: () -> Unit) {
    group("describe: $description", body = body)
}

/**
 * Creates an [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.context(description: String, body: () -> Unit) {
    group("context: $description", body = body)
}

/**
 * Creates an [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.given(description: String, body: () -> Unit) {
    group("given: $description", body = body)
}

/**
 * Creates an [example][Dsl.example].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.it(description: String, body: () -> Unit) {
    example("it: $description", body = body)
}

/**
 * Creates an [example][Dsl.example].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.on(description: String, body: () -> Unit) {
    example("on: $description", body = body)
}

/**
 * Creates a pending [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.xdescribe(description: String, reason: String? = null, body: () -> Unit) {
    group("[pending] describe: $description", Pending.Yes(reason), body)
}

/**
 * Creates a pending [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.xcontext(description: String, reason: String? = null, body: () -> Unit) {
    group("[pending] context: $description", Pending.Yes(reason), body)
}

/**
 * Creates a pending [example group][Dsl.group].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.xgiven(description: String, reason: String? = null, body: () -> Unit) {
    group("[pending] given: $description", Pending.Yes(reason), body)
}

/**
 * Creates a pending [example][Dsl.example].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.xit(description: String, reason: String? = null, body: () -> Unit) {
    example("[pending] it: $description", Pending.Yes(reason), body)
}

/**
 * Creates a pending [example][Dsl.example].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun Dsl.xon(description: String, reason: String? = null, body: () -> Unit) {
    example("[pending] on: $description", Pending.Yes(reason), body)
}


/**
 * Creates an [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.describe(subject: KClass<T>, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "describe: ${subject.qualifiedName}", body = body)
}

/**
 * Creates an [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.context(subject: KClass<T>, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "context: ${subject.qualifiedName}", body = body)
}

/**
 * Creates an [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.given(subject: KClass<T>, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "given: ${subject.qualifiedName}", body = body)
}

/**
 * Creates a pending [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.xdescribe(subject: KClass<T>, reason: String? = null, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "[pending] describe: ${subject.qualifiedName}", Pending.Yes(reason), body = body)
}

/**
 * Creates a pending [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.xcontext(subject: KClass<T>, reason: String? = null, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "[pending] context: ${subject.qualifiedName}", Pending.Yes(reason), body = body)
}

/**
 * Creates a pending [example group][Dsl.group] with a [subject][org.jetbrains.spek.subject.Subject].
 *
 * @author Ranie Jade Ramiso
 * @since 1.0
 */
fun <T: Any> Dsl.xgiven(subject: KClass<T>, reason: String? = null, body: SubjectDsl<T>.() -> Unit) {
    group(subject, "[pending] given: ${subject.qualifiedName}", Pending.Yes(reason), body = body)
}
