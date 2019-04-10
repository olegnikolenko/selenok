import matchers.DisplayedMatcher.displayed
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ui.injectPage

class YandexSearchTest {

    private val yaSearchPage by injectPage<YaSearchPage>()

    @BeforeEach
    fun setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\onikolenko\\IdeaProjects\\selenok\\src\\test\\resources\\chromedriver.exe")
    }

    @Test
    fun testSearchPage() {

        yaSearchPage.open()
            .searchBlock
            .homeTabsBlock
            .videoLink
            .should(displayed())

        println("")
    }
}