import org.openqa.selenium.By.xpath
import ui.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class YaSearchPage: Page<YaSearchPage>() {

    val searchBlock by element<SearchBlock> {
        description = "Search Block"
        locator = xpath(".//div[contains(@class, 'container__search')]")
    }

    override fun getPage(): YaSearchPage {
        return this
    }

    override fun open(): YaSearchPage {
        webDriver.get("https://yandex.ru")
        return getPage()
    }
}

class SearchBlock: AbstractElement() {
    val homeTabsBlock by element<HomeTabsBlock>{
        description = "Home Tabs Block"
        locator = xpath(".//div[contains(@class, 'home-tabs')]")
    }
}

class HomeTabsBlock: AbstractElement(){
    val videoLink by element<Element> {
        description = "Video Link"
        locator = xpath(".//a[@data-id='video']")
    }
}

