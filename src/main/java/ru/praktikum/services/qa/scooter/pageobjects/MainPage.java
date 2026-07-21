package ru.praktikum.services.qa.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    //кнопка вопрос аккордиона
    private final String accordionQuestionButton = ".//div[@class='accordion__item'][%s]//div[@class='accordion__button']";

    //панель с ответом аккордиона
    private final String accordionAnswerPanel = ".//div[@class='accordion__item'][%s]//div[@class='accordion__panel']";

    public void openUrl() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public String getAccordionQuestionText(String elementNumber) {
        String locator = String.format(accordionQuestionButton, elementNumber);
        WebElement button = driver.findElement(By.xpath(locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        return button.getText();
    }

    public WebElement getAccordionAnswerPanel(String elementNumber) {
        String locator = String.format(accordionAnswerPanel, elementNumber);
        WebElement answer = driver.findElement(By.xpath(locator));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(answer));
        return answer;
    }

    public boolean isAccordionPanelIsVisible(String elementNumber) {
        WebElement answer = getAccordionAnswerPanel(elementNumber);
        return answer.isDisplayed();
    }

    public String getAccordionAnswerPanelText(String elementNumber) {
        WebElement answer = getAccordionAnswerPanel(elementNumber);
        return answer.getText();
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
