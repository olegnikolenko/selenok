package ui

import org.openqa.selenium.*
import java.util.*
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: AbstractElement> element(block: ElementBuilder.() -> Unit): ReadOnlyProperty<Any, T> = ElementBuilder().apply(block).build()

class ElementBuilder {

    var description: String by Delegates.notNull()

    var locator: By by Delegates.notNull()

    inline fun <reified T: AbstractElement>build(): ReadOnlyProperty<Any, T> = lazy {
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


