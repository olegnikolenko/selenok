package ui

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

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