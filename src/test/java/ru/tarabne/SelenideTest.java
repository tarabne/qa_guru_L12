package ru.tarabne;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {
    @Test
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;
        open("https://github.com/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#870")).should(Condition.exist);

    }
}
