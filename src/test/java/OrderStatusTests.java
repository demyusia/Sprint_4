import ru.praktikum.services.qa.scooter.driver.FactoryDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;
import ru.praktikum.services.qa.scooter.pageobjects.StatusPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.util.TestUtil;

public class OrderStatusTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void fillInIncorrectOrderNumberShowsStatusPageWithMessageTest () {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        mainPage.clickOnStatusButton();
        mainPage.fillInOrderNumber("2313543245");
        StatusPage statusPage = mainPage.clickOnGoButton();

        Assert.assertTrue(statusPage.isImgIncorrectOrderNumberDisplayed());
    }
}
