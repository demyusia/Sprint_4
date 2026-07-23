package ru.praktikum.services.qa.scooter.util;

import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;

public class TestUtil {
    public static MainPage openMainPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();
        return mainPage;
    }
}
