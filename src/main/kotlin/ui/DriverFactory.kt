package ui

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.concurrent.getOrSet

val webDriver by lazy {
    ThreadLocal<WebDriver>().getOrSet {
        DriverType.valueOf("CHROME").createDiver()
    }
}

val webDriverWait by lazy {
    ThreadLocal<WebDriverWait>().getOrSet {
        WebDriverWait(webDriver, 10)
    }
}

enum class DriverType: DriverFactory {
    CHROME {
        override fun createDiver(): WebDriver {
            return ChromeDriver()
        }
    };
}

interface DriverFactory {
    fun createDiver(): WebDriver
}