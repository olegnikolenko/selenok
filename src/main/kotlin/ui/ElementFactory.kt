package ui

import org.openqa.selenium.*
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: AbstractElement>injectElement(description: String, locator: By): ReadOnlyProperty<Any, T> {
    return lazy {
        val currentElem = T::class.java.newInstance()
        currentElem.locator = locator
        currentElem.description = description
        object : ReadOnlyProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T {
                when(thisRef) {
                    is Page<*> -> {
                        currentElem.driver = thisRef.webDriver
                        currentElem.driverWait = thisRef.webDriverWait
                        currentElem.contextElems = LinkedList()
                        currentElem.contextElems.offerFirst(currentElem)

                    }
                    is AbstractElement -> {
                        currentElem.driver = thisRef.driver
                        currentElem.driverWait = thisRef.driverWait
                        currentElem.contextElems = thisRef.contextElems
                        currentElem.contextElems.add(currentElem)
                    }
                }
                return currentElem
            }
        }
    }.value
}

