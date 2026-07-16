import Driver.FactoryDriver;
import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MakeOrderTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void errorTextIsDisplayedWhenIncorrectDataFilledInOrderInputsTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openUrl();

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
