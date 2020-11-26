import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class LambdaStepTests {

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
    public void lambdaStepTest () {
        step("Открываем главную github", () -> {
            open("https://github.com/");
        });
        step("Открываем форму для логина", () -> {
            $(byText("Sign in")).click();
        });
        step("Заполняем поля логин и пароль", (step) -> {
            step.parameter("name", name);
            $("#login_field").val(name);
            $("#password").val(password);
        });
        step("Входим по кнопке submit", () -> {
            $(byName("commit")).click();
        });
        step("Ищем репозиторий " + SearchFor, (step) -> {
            step.parameter("name", SearchFor);
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(SearchFor);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + SearchFor, (step) -> {
            step.parameter("name", SearchFor);
            $(By.linkText(SearchFor)).click();
        });
        step("Переходим в Issues", () -> {
            $(withText("Actions")).parent().parent().preceding(1).click();
        });
        step("Добавляем New issue", () -> {
            $(byLinkText("New issue")).click();
        });
        step("Заполняем поля для ввода", () -> {
            $("#issue_title").val(title);
            $("#issue_body").val(comment);
            $(byText("assign yourself")).click();
            $("#labels-select-menu").click();
            sleep(1000);
            $("#label-filter-field").val(label).pressTab().pressEnter().pressEscape();
        });
        step("Отправляем Issue", () -> {
            $(byText("Submit new issue")).click();
        });
        step("Проверяем, что данные добавлены", () -> {
            $("#show_issue").shouldHave(text(title));
            $("#show_issue").shouldHave(text(comment));
            $(".TimelineItem-body").shouldHave(text(name));
            $("#show_issue").shouldHave(text(label));
            closeWindow();
        });
    }
}

