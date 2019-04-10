import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

abstract class Page<T: Page<T>> {

    private val context: PageContext by lazy {
        PageContext()
    }

    val webDriver: WebDriver by lazy {
        when (val driverThread = context.map[driver]) {
            is ThreadLocal<*> -> driverThread.get() as WebDriver
            else -> throw IllegalArgumentException()
        }
    }

    val webDriverWait: WebDriverWait by lazy {
        when (val driverWait = context.map[driverWait]) {
            is WebDriverWait -> driverWait
            else -> throw IllegalArgumentException()
        }
    }

    abstract fun open(): T
    abstract fun getPage(): T
}
