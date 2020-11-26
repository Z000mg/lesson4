import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class IssueTests {

    private static final String SearchFor = "Z000mg/lesson4";
    private static final String name = "Z000mg";
    private static final String title = "My first title!";
    private static final String comment = "How can I install the allure without pain?!";
    private static final String label = "good first issue";
    private static final String password = "";

    @BeforeAll
    static void setup(){
        Configuration.startMaximized = true;
    }

    @Test
    public void addIssueClearTest () {
        open("https://github.com/");
        $(byText("Sign in")).click();
        $("#login_field").val(name);
        $("#password").val(password);
        $(byName("commit")).click();
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(SearchFor);
        $(".header-search-input").submit();
        $(By.linkText(SearchFor)).click();
        $(withText("Actions")).parent().parent().preceding(1).click();
        $(byLinkText("New issue")).click();
        $("#issue_title").val(title);
        $("#issue_body").val(comment);
        $(byText("assign yourself")).click();
        $("#labels-select-menu").click();
        sleep(1000);
        $("#label-filter-field").val(label).pressTab().pressEnter().pressEscape();
        $(byText("Submit new issue")).click();
        $("#show_issue").shouldHave(text(title));
        $("#show_issue").shouldHave(text(comment));
        $(".TimelineItem-body").shouldHave(text(name));
        $("#show_issue").shouldHave(text(label));
        closeWindow();
    }
}

