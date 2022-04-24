
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaSelenideTest {
    private static final String REPOSITORY = "mryabova2/qa_guru_allure";
    private static final int ISSUE_NUMBER = 1;
    @Test
    public void testIssue () {
        SelenideLogger.addListener("allure",new AllureSelenide());
        step("Открываем главную страницу", () -> open("https://github.com/"));
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> $(linkText("mryabova2/qa_guru_allure")).click());
        step("Кликаем на таб Issues", () -> $(partialLinkText("Issues")).click());
        step("Проверяем, что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#"+ISSUE_NUMBER)).shouldBe(visible);
            Allure.getLifecycle().addAttachment(
                    "Страница",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
