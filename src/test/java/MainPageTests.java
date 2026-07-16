import Driver.FactoryDriver;
import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void accordionClickTest () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();

        List<WebElement> elements = mainPage.getElementsOfAccordion();
        for (WebElement element : elements) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            WebElement button = mainPage.getAccordionQuestionButton(element);
            button.click();
            WebElement answer = mainPage.getAccordionAnswerPanel(element);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(answer));
            Assert.assertTrue(answer.isDisplayed());
        }
    }

    @Test
    public void clickOnLogoScooterBringsToMainPageOfScooterTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();

        OrderPage orderPage = mainPage.goToOrderPageFromHeader();
        orderPage.clickOnLogoScooter();

        Assert.assertTrue(mainPage.isMainPageScooterOpen());

    }

    @Test
    public void clickOnLogoYandexBringsToMainPageOfYandexTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();

        mainPage.clickOnLogoYandex();
        Assert.assertTrue(driver.getPageSource().contains("yandex.ru"));

    }
}
