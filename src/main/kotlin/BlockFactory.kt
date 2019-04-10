import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class Element {
    open lateinit var description: String
    open lateinit var locator: By
    open lateinit var driver: WebDriver
    open lateinit var driverWait: WebDriverWait
    open lateinit var contextElems: LinkedList<Element>

    private fun find(): WebElement {
        val elemIterator = contextElems.iterator()
        var elementToFind = elemIterator.next()
        var foundWebElement: WebElement
        try {
            foundWebElement = driverWait.until{
                it.findElement(elementToFind.locator)
            }
            while (elemIterator.hasNext()) {
                elementToFind = elemIterator.next()
                driverWait.until {
                    foundWebElement = foundWebElement.findElement(elementToFind.locator)
                }
            }
        } catch (ex: TimeoutException) {
            println("При поиске элемента $description [$locator] в цепочке вызовов упали на:\n")
            contextElems.forEach {
                if (it == elementToFind) {
                    println("[]=> ${it.description} [${it.locator}]")
                } else println(it.description)
            }
            throw ex
        }
        return foundWebElement
    }

    fun click() {
        val webElement = find()
        driverWait.until(elementToBeClickable(webElement))
        webElement.click()
    }

    fun should(message: String, matcher: Matcher<WebElement>) {
        assertThat<WebElement>(message, find(), matcher)
    }
    fun should(matcher: Matcher<WebElement>) {
        assertThat(find(), matcher)
    }
}

class LeafElement: Element()

inline fun <reified T: Element>injectElement(description: String, locator: By): ReadOnlyProperty<Any, T> {
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
                    is Element -> {
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


