import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class AnnotationStepTests {

    private static final String repository = "Z000mg/lesson4";
    private static final String myName = "Z000mg";
    private static final String title = "My first title!";
    private static final String comment = "How can I install the allure without pain?!";
    private static final String label = "good first issue";
    private static final String password = "";

    @BeforeAll
    static void setup(){
        Configuration.startMaximized = true;
    }

    @Test
    public void inputByAnnotationStep() {
        final annotationTest steps = new annotationTest();

        steps.openMainPage();
        steps.openLoginPage();
        steps.inputLoginAndPassword(myName);
        steps.submitClick();
        steps.findRepository(repository);
        steps.goToRepository(repository);
        steps.goToIssues();
        steps.makeNewIssue();
        steps.fillFields();
        steps.submitIssue();
        steps.assertIssue();
    }

    public static class annotationTest {

        @Step("Открываем главную github")
        public void openMainPage() {
            open("https://github.com/");
        }

        @Step("Открываем форму для логина")
        public void openLoginPage() {
            $(byText("Sign in")).click();
        }

        @Step("Заполняем поля логин и пароль {name}")
        public void inputLoginAndPassword(final String name) {
            $("#login_field").val(myName);
            $("#password").val(password);
        }

        @Step("Входим по кнопке submit")
        public void submitClick() {
            $(byName("commit")).click();
        }

        @Step("Ищем репозиторий {name}")
        public void findRepository(final String name) {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repository);
            $(".header-search-input").submit();
        }

        @Step("Переходим в репозиторий {name}")
        public void goToRepository(final String name) {
            $(By.linkText(name)).click();
        }

        @Step("Переходим в Issues")
        public void goToIssues() {
            $(withText("Actions")).parent().parent().preceding(1).click();
        }

        @Step("Добавляем New issue")
        public void makeNewIssue() {
            $(byLinkText("New issue")).click();
        }

        @Step("Заполняем поля для ввода")
        public void fillFields() {
            $("#issue_title").val(title);
            $("#issue_body").val(comment);
            $(byText("assign yourself")).click();
            $("#labels-select-menu").click();
            sleep(1000);
            $("#label-filter-field").val(label).pressTab().pressEnter().pressEscape();
        }

        @Step("Отправляем Issue")
        public void submitIssue() {
            $(byText("Submit new issue")).click();
        }

        @Step("Проверяем, что данные добавлены")
        public void assertIssue() {
            $("#show_issue").shouldHave(text(title));
            $("#show_issue").shouldHave(text(comment));
            $(".TimelineItem-body").shouldHave(text(myName));
            $("#show_issue").shouldHave(text(label));
        }
    }
}