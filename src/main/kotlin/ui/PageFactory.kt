package ui

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: Page<T>>page(): ReadOnlyProperty<Any, T> {
    return lazy {
        val instance = T::class.constructors.first {
            it.parameters.isEmpty()
        }.call()
        object : ReadOnlyProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T {
                return instance
            }
        }
    }.value
}


