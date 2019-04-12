package ui

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

const val driver = "driver"
const val driverWait = "driverWait"

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

class PageContext {
    val map: HashMap<String, Any> = hashMapOf()

    init{
        map[driver] = ThreadLocal.withInitial<WebDriver> {
            DriverType.valueOf("CHROME").createDiver()
        }
        map[driverWait] = WebDriverWait(
            (map[driver] as ThreadLocal<*>).get() as WebDriver, 10
        )
    }

}



