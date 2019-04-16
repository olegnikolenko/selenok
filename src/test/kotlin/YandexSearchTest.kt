import matchers.DisplayedMatcher.Companion.displayed
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ui.page

class YandexSearchTest {

    private val yaSearchPage by page<YaSearchPage>()
    private val yaResultPage by page<YaResultPage>()

    @BeforeEach
    fun setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\onikolenko\\IdeaProjects\\selenok\\src\\test\\resources\\chromedriver.exe")
    }

    @Test
    fun testSearchPage() {

        yaSearchPage {
            open()
            searchBlock {
                findText("find some text")
            }
        }
        yaResultPage {
            navigationBlock.videoLink.should(displayed())
        }
        println("")
    }
}