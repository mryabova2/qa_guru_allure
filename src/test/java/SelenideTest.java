
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideTest {
    @Test
    public void testIssue () {
        SelenideLogger.addListener("allure",new AllureSelenide());

        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue("mryabova2/qa_guru_allure");
        $(".header-search-input").submit();
        $(linkText("mryabova2/qa_guru_allure")).click();
        $(partialLinkText("Issues")).click();
        $(withText("#1")).shouldBe(visible);

    }
}
