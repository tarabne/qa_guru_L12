package ru.tarabne;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    @Feature("Какая-то фича")
    @Story("Создание Issue")
    @Owner("tarabne")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    void testStaticLabels() {

    }
    @Test
    void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя")
        );
        Allure.feature("Какая-то фича");
        Allure.story("Создание Issue");
        Allure.label("owner", "tarabne");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");

    }

}
