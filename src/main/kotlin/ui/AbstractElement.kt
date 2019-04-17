package ui

import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import java.util.*

abstract class AbstractElement {
    open lateinit var description: String
    open lateinit var locator: By
    open lateinit var contextElems: LinkedList<AbstractElement>

    fun sendKeys(str: String) {
        find().sendKeys(str)
    }


    fun find(): WebElement {
        val elemIterator = contextElems.iterator()
        var elementToFind = elemIterator.next()
        var foundWebElement: WebElement
        try {
            foundWebElement = webDriverWait.until{
                it.findElement(elementToFind.locator)
            }
            while (elemIterator.hasNext()) {
                elementToFind = elemIterator.next()
                webDriverWait.until {
                    foundWebElement = foundWebElement.findElement(elementToFind.locator)
                }
            }
        } catch (ex: TimeoutException) {
            println("Не нашли элемент $description [$locator]:\n")
            contextElems.forEach {
                if (it == elementToFind) {
                    println("из за []=> ${it.description} [${it.locator}]")
                } else println(it.description)
            }
            throw ex
        }
        return foundWebElement
    }

    fun click() {
        val webElement = find()
        webDriverWait.until(elementToBeClickable(webElement))
        webElement.click()
    }

    fun should(message: String, matcher: Matcher<AbstractElement>): AbstractElement {
        assertThat<AbstractElement>(message, this,  matcher)
        return this
    }

    fun should(matcher: Matcher<AbstractElement>): AbstractElement {
        assertThat(this, matcher)
        return this
    }

    fun wait(matcher: Matcher<AbstractElement>): AbstractElement {
        wait(this, matcher)
        return this
    }

    private fun wait(element: AbstractElement, matcher: Matcher<AbstractElement>): AbstractElement {
        webDriverWait.until{ matcher.matches(element)}
        return this
    }

    override fun toString(): String {
        return "$description: $locator"
    }
}

class Element: AbstractElement()