import Driver.FactoryDriver;
import PageObjects.MainPage;
import PageObjects.StatusPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class OrderStatusTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void fillInIncorrectOrderNumberShowsStatusPageWithMessageTest () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();

        mainPage.clickOnStatusButton();
        mainPage.fillInOrderNumber("2313543245");
        StatusPage statusPage = mainPage.clickOnGoButton();

        Assert.assertTrue(statusPage.isImgIncorrectOrderNumberDisplayed());
    }
}
