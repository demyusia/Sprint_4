import ru.praktikum.services.qa.scooter.driver.FactoryDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;
import ru.praktikum.services.qa.scooter.pageobjects.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.util.TestUtil;

public class MakeOrderTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void errorTextIsDisplayedWhenIncorrectDataFilledInOrderInputsTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        OrderPage orderPage = mainPage.goToOrderPageFromHeader();
        String wrong = "wrong";
        orderPage.fillInInfoAboutYourselfWithData(wrong, wrong, wrong, wrong, wrong);
        System.out.println("Ok");

        Assert.assertTrue(orderPage.isMessageWrongNameIsDisplayed());
        Assert.assertTrue(orderPage.isMessageWrongSurnameIsDisplayed());
        Assert.assertTrue(orderPage.isMessageWrongAddressIsDisplayed());
        Assert.assertTrue(orderPage.isMessageWrongStationIsDisplayed());
        Assert.assertTrue(orderPage.isMessageWrongPhoneNumberIsDisplayed());
    }
}
