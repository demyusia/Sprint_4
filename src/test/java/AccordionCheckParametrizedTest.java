import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.driver.FactoryDriver;
import ru.praktikum.services.qa.scooter.pageobjects.MainPage;
import ru.praktikum.services.qa.scooter.util.TestUtil;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AccordionCheckParametrizedTest {

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    private final String elementNumber;
    private final String question;
    private final String answer;

    public AccordionCheckParametrizedTest(String elementNumber, String question, String answer) {
        this.elementNumber = elementNumber;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getFillInData() {
        return Arrays.asList(new Object[][]{
                {"1", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"2", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"3", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"4", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"5", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"6", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"7", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"8", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });
    }

    @Test
    public void AccordionCheckTest() {
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = TestUtil.openMainPage(driver);

        String actualQuestion = mainPage.getAccordionQuestionText(elementNumber);
        Assert.assertEquals(question, actualQuestion);
        Assert.assertTrue(mainPage.isAccordionPanelIsVisible(elementNumber));

        String actualAnswer = mainPage.getAccordionAnswerPanelText(elementNumber);
        Assert.assertEquals(answer, actualAnswer);
    }
}
