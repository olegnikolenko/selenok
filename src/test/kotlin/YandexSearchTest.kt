import matchers.DisplayedMatcher.Companion.displayed
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ui.page

class YandexSearchTest {

    private val yaSearchPage by page<YaSearchPage>()

    @BeforeEach
    fun setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\onikolenko\\IdeaProjects\\selenok\\src\\test\\resources\\chromedriver.exe")
    }

    @Test
    fun testSearchPage() {

        yaSearchPage {
            searchBlock
            .homeTabsBlock
            .videoLink
            .should(displayed()).click()
        }

        println("")
    }
}