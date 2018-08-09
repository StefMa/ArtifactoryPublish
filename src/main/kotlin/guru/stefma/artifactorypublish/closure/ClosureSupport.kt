package guru.stefma.artifactorypublish.closure

import groovy.lang.Closure
import org.gradle.internal.Cast.uncheckedCast

// Support for Groovys Closures
// copied from https://git.io/fNH3u

/**
 * Adapts a Kotlin function to a single argument Groovy [Closure].
 *
 * @param T the expected type of the single argument to the closure.
 * @param action the function to be adapted.
 *
 * @see [KotlinClosure1]
 */
fun <T> Any.closureOf(action: T.() -> Unit): Closure<Any?> =
        KotlinClosure1(action, this, this)

/**
 * Adapts an unary Kotlin function to an unary Groovy [Closure].
 *
 * @param T the type of the single argument to the closure.
 * @param V the return type.
 * @param function the function to be adapted.
 * @param owner optional owner of the Closure.
 * @param thisObject optional _this Object_ of the Closure.
 *
 * @see [Closure]
 */
class KotlinClosure1<in T : Any?, V : Any>(
        val function: T.() -> V?,
        owner: Any? = null,
        thisObject: Any? = null
) : Closure<V?>(owner, thisObject) {

    @Suppress("unused") // to be called dynamically by Groovy
    fun doCall(it: T): V? = it.function()
}