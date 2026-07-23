import ru.praktikum.services.qa.scooter.driver.FactoryDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;
import ru.praktikum.services.qa.scooter.pageobjects.OrderPage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.util.TestUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MakeOrderParametrizedTests {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final LocalDate date;
    private final String period;
    private final boolean blackScooter;
    private final boolean greyScooter;
    private final String comments;

    public MakeOrderParametrizedTests(String name, String surname, String address, String station, String phoneNumber, LocalDate date, String period, boolean blackScooter, boolean greyScooter, String comments) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.blackScooter = blackScooter;
        this.greyScooter = greyScooter;
        this.comments = comments;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getFillInData() {
            LocalDate firstDate = LocalDate.now();
            LocalDate secondDate = firstDate.plusDays(10);
            return Arrays.asList(new Object[][] {
                    {"Шерлок", "Хомс", "ул.Бейкер, 221", "Черкизовская", "89111111111", firstDate, "трое суток", true, false, "очень жду"},
                    {"Гарри", "Поттер", "ул.Тисовая, 4", "Сокольники", "89999999999", secondDate, "пятеро суток", false, true, "no comments"},
            });
    }

    @Test
    public void makeOrderWithCorrectDataTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);


        OrderPage orderPage = mainPage.goToOrderPageFromHeader();           //проверяем кнопку Заказать в заголовке
        Assert.assertTrue(orderPage.isOrderPageIsVisible());
        mainPage = orderPage.goToPreviousPage();                            // возвращаемся на главную страницу

        orderPage = mainPage.goToOrderPageFromBottom();
        Assert.assertTrue(orderPage.isOrderPageIsVisible());                // проверяем кнопку Заказать снизу

        orderPage.fillInInfoAboutYourselfWithData(name, surname, address, station, phoneNumber);
        orderPage.fillInInfoAboutRentWithData(date, period, blackScooter, greyScooter, comments);
        Assert.assertTrue(orderPage.isHeaderOrderOkIsDisplayed());

    }
}
