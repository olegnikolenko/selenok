import com.sun.org.glassfish.gmbal.Description
import org.openqa.selenium.By.xpath

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

class SearchBlock: Element() {

    val homeTabsBlock by injectElement<HomeTabsBlock>(
        description = "Home Tabs Block",
        locator = xpath(".//div[contains(@class, 'home-tabs')]")
    )
}

class HomeTabsBlock: Element(){
    val videoLink by injectElement<LeafElement>(
        description = "Video Link",
        locator = xpath(".//a[@data-id='video']")
    )
}

