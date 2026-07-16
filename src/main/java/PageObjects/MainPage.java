package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка Заказать(перейти на форму заказа) сверху
    private final By goToOrderPageButtonUp = By.xpath(".//div[@class='Header_Nav__AGCXC']/Button[text()='Заказать']");

    //кнопка Заказать(перейти на форму заказа) снизу
    private final By goToOrderPageButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/Button[text()='Заказать']");

    //заголовок главной страницы
    private final By headerMainPage = By.className("Home_Header__iJKdX");

    //логотип Яндекса
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");

    //кнопка статус заказа
    private final By statusButton = By.className("Header_Link__1TAG7");

    //поле для ввода номера заказа
    private final By orderNumberInput = By.className("Header_Input__xIoUq");

    //кнопка Go
    private final By goButton = By.className("Header_Button__28dPO");

    //выпадающий список вопросов о важном
    private final By faqAccordion = By.xpath(".//div[@class='accordion']/*");

    //кнопка вопрос аккордиона
    private final By accordionQuestionButton = By.className("accordion__button");

    //панель с ответом аккордиона
    private final By accordionAnswerPanel = By.className("accordion__panel");

    public void openUrl() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public List<WebElement> getElementsOfAccordion() {
        return driver.findElements(faqAccordion);
    }

    public WebElement getAccordionQuestionButton(WebElement element) {
        return element.findElement(accordionQuestionButton);
    }

    public WebElement getAccordionAnswerPanel(WebElement element) {
        return element.findElement(accordionAnswerPanel);
    }

    public OrderPage goToOrderPageFromHeader () {
        driver.findElement(goToOrderPageButtonUp).click();
        return new OrderPage(driver);
    }

    public OrderPage goToOrderPageFromBottom() {
        WebElement orderButton = driver.findElement(goToOrderPageButtonDown);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderButton);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(goToOrderPageButtonDown));
        orderButton.click();
        return new OrderPage(driver);
    }

    public boolean isMainPageScooterOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(headerMainPage));
        return driver.findElement(headerMainPage).isDisplayed();
    }

    public void clickOnLogoYandex () {
        driver.findElement(logoYandex).click();
    }

    public void clickOnStatusButton() {
        driver.findElement(statusButton).click();
    }

    public void fillInOrderNumber (String orderNumb) {
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        driver.findElement(orderNumberInput).clear();
        driver.findElement(orderNumberInput).sendKeys(orderNumb);
    }

    public StatusPage clickOnGoButton() {
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }

}
