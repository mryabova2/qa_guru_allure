
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotatedTest {
    private static final String REPOSITORY = "mryabova2/qa_guru_allure";
    private static final int ISSUE_NUMBER = 1;

    @Owner("mvryabova2")
    @Link(value = "Тестинг", url = "https://github.com")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Задачи в репозитории")
    @Story("Просмотр созданных задач в репозитории")

    @Test
    @DisplayName("Тест")
    public void testIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.clickOnIssue();
        steps.checkIssueNumber(ISSUE_NUMBER);
    }
}
