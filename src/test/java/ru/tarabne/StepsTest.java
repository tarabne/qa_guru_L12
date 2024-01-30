package ru.tarabne;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.io.Reader;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Переходим на вкладку issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем, что среди issues есть issue c номером #" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
