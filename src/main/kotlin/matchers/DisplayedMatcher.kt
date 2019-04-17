package matchers

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher
import org.openqa.selenium.TimeoutException
import ui.AbstractElement
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf
import ui.webDriverWait

class DisplayedMatcher : TypeSafeMatcher<AbstractElement>() {

    lateinit var element: AbstractElement

    override fun matchesSafely(element: AbstractElement): Boolean {
        this.element = element
        return try {
            val webElement = element.find()
            webDriverWait.until(visibilityOf(webElement))
            webElement.isDisplayed
        } catch (e: TimeoutException) {
            false
        }
    }

    override fun describeTo(description: Description) {
        description.appendValue(element).appendText(" is displayed on page")
    }

    public override fun describeMismatchSafely(element: AbstractElement, mismatchDescription: Description) {
        mismatchDescription.appendValue(element).appendText(" is not displayed on page")
    }

    companion object {
        @Factory
        fun displayed(): DisplayedMatcher {
            return DisplayedMatcher()
        }
    }

}