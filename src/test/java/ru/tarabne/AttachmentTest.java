package ru.tarabne;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment("Source",webdriver().driver().source());
        });
    }

    @Test
    public void testAnnotatedAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();
        steps.searchForRepository(REPOSITORY);
        steps.takeScreenshot();
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.takeScreenshot();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
