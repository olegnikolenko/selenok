import org.openqa.selenium.By
import ui.Page
import ui.element

class YaResultPage: Page<YaResultPage>() {

    val navigationBlock by element<NavigationBlock> {
        description = "Search Block"
        locator = By.xpath(".//div[@class = 'navigation__region']")
    }

    override fun invoke(block: YaResultPage.() -> Unit) {
        block()
    }

    override fun open(): YaResultPage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPage(): YaResultPage {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}