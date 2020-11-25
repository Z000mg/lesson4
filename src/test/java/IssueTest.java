import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueTest {

    private static String Repo = "https://github.com/Z000mg/lesson4/issues";
    private static String User = "Z000mg";
    private static String IssueNumber = 1;

    @Test
    public void searchForIssue {
        open("https://github.com/");
        $(".header-search-input").click();
    }
}

