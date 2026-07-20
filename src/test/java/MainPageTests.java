import ru.praktikum.services.qa.scooter.driver.FactoryDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;
import ru.praktikum.services.qa.scooter.pageobjects.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.praktikum.services.qa.scooter.util.TestUtil;

import java.util.List;

public class MainPageTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void accordionClickTest () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        List<WebElement> elements = mainPage.getElementsOfAccordion();
        for (WebElement element : elements) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            WebElement button = mainPage.getAccordionQuestionButton(element);
            button.click();
            WebElement answer = mainPage.getAccordionAnswerPanel(element);
            Assert.assertTrue(answer.isDisplayed());
        }
    }

    @Test
    public void clickOnLogoScooterBringsToMainPageOfScooterTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        OrderPage orderPage = mainPage.goToOrderPageFromHeader();
        orderPage.clickOnLogoScooter();

        Assert.assertTrue(mainPage.isMainPageScooterOpen());

    }

    @Test
    public void clickOnLogoYandexBringsToMainPageOfYandexTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        mainPage.clickOnLogoYandex();
        Assert.assertTrue(driver.getPageSource().contains("yandex.ru"));

    }
}
