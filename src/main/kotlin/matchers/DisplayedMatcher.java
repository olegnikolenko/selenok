package matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ui.Element;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DisplayedMatcher extends TypeSafeMatcher<Element> {

    @Override
    protected boolean matchesSafely(Element element) {
        try {
            WebElement webElement = element.find();
            element.driverWait.until(visibilityOf(webElement));
            return webElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void describeTo(Description description) {
        description.appendText("element is displayed on page");
    }

    @Override
    public void describeMismatchSafely(Element element, Description mismatchDescription) {
        mismatchDescription.appendValue(element).
                appendText(" is not displayed on page");
    }

    @Factory
    public static DisplayedMatcher displayed() {
        return new DisplayedMatcher();
    }

}