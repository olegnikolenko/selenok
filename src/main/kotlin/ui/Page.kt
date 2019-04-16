package ui

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

abstract class Page<T: Page<T>> {

    private val context: PageContext by lazy {
        PageContext()
    }

    val webDriver: WebDriver by lazy { context.map[driver] as WebDriver }

    val webDriverWait: WebDriverWait by lazy { context.map[driverWait] as WebDriverWait }

    abstract fun open(): T
    abstract fun getPage(): T
    abstract operator fun invoke(block: T.() -> Unit)
}
