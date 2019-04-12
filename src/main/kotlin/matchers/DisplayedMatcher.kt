package matchers

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher
import org.openqa.selenium.TimeoutException
import ui.AbstractElement
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf

class DisplayedMatcher : TypeSafeMatcher<AbstractElement>() {

    override fun matchesSafely(element: AbstractElement): Boolean {
        return try {
            val webElement = element.find()
            element.driverWait.until(visibilityOf(webElement))
            webElement.isDisplayed
        } catch (e: TimeoutException) {
            false
        }
    }

    override fun describeTo(description: Description) {
        description.appendText("element is displayed on page")
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