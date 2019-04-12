import org.openqa.selenium.By.xpath
import ui.AbstractElement
import ui.Element
import ui.Page
import ui.injectElement

class YaSearchPage: Page<YaSearchPage>() {

    val searchBlock by injectElement<SearchBlock>(
        description = "Search Block",
        locator = xpath(".//div[contains(@class, 'container__search')]")
    )

    override fun getPage(): YaSearchPage {
        return this
    }

    override fun open(): YaSearchPage {
        webDriver.get("https://yandex.ru")
        return getPage()
    }
}

class SearchBlock: AbstractElement() {

    val homeTabsBlock by injectElement<HomeTabsBlock>(
        description = "Home Tabs Block",
        locator = xpath(".//div[contains(@class, 'home-tabs')]")
    )
}

class HomeTabsBlock: AbstractElement(){
    val videoLink by injectElement<Element>(
        description = "Video Link",
        locator = xpath(".//a[@data-id='video']")
    )
}

