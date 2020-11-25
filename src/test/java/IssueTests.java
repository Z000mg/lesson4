import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueTests {

    private static final String SearchFor = "Z000mg/lesson4";
    private static final int IssueNumber = 1;

    @Test
    public void searchForIssue () {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(SearchFor);
        $(".header-search-input").submit();
        $(By.linkText(SearchFor)).click();
        $(withText("Issues")).click();
        $(withText("#" + IssueNumber)).should(Condition.exist);
    }
}

