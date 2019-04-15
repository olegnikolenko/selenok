import org.openqa.selenium.By.xpath
import ui.*

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

    override operator fun invoke(block: YaSearchPage.() -> Unit) {
        block()
    }
}

class SearchBlock: AbstractElement() {
    val homeTabsBlock by element<HomeTabsBlock>{
        description = "Home Tabs Block"
        locator = xpath(".//div[contains(@class, 'home-tabs')]")
    }

    val input by element<Element> {
        description = "Input field"
        locator = xpath("//input[contains(@class, 'input__control')]")
    }

    val findButton by element<Element> {
        description = "Find"
        locator = xpath("//button")
    }

    fun findText(textToFind: String){
        input.sendKeys(textToFind)
        findButton.click()
    }

    operator fun invoke(block: SearchBlock.() -> Unit) {
        block()
    }

}

class HomeTabsBlock: AbstractElement(){
    val videoLink by element<Element> {
        description = "Video Link"
        locator = xpath(".//a[@data-id='video']")
    }
}

