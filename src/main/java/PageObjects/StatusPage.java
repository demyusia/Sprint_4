package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StatusPage {
    private WebDriver driver;

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    //картинка Такого заказа нет
    private final By wrongOrderNumber = By.xpath(".//img[@alt='Not found']");

    public boolean isImgIncorrectOrderNumberDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(wrongOrderNumber));
        return driver.findElement(wrongOrderNumber).isDisplayed();
    }

}
