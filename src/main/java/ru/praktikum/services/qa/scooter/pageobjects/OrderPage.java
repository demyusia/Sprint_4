package ru.praktikum.services.qa.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле для ввода Имени
    private final By nameInput = By.cssSelector("Input[placeholder='* Имя']");

    // поле для ввода фамилии
    private final By surnameInput = By.cssSelector("Input[placeholder='* Фамилия']");

    //поле для ввода адреса
    private final By addressInput = By.cssSelector("Input[placeholder='* Адрес: куда привезти заказ']");

    //поле для выбора станции
    private final By stationInput = By.className("select-search__input");

    //выбор станции в списке
    private final String stationChoose = ".//div[text()='%s']/parent::button";

    //поле для ввода телефона
    private final By phoneNumberInput = By.cssSelector("Input[placeholder='* Телефон: на него позвонит курьер']");

    //поле даты аренды
    private final By dateInput = By.cssSelector("Input[placeholder='* Когда привезти самокат']");

    //поле срок аренды
    private final By periodOfRentInput = By.className("Dropdown-root");

    //выбор срока аренды в списке
    private final String periodOfRentChoose = ".//div[@class='Dropdown-option' and text()='%s']";

    //поле цвет самоката черный
    private final By colourBlackCheckbox = By.id("black");

    //поле цвет самоката серый
    private final By colourGreyCheckbox = By.id("grey");

    //поле для комментария
    private final By commentInput = By.cssSelector("Input[placeholder='Комментарий для курьера']");

    //кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    //кнопка Заказать (закончить оформление заказа)
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/Button[text()='Заказать']");

    //кнопка подтверждения заказа "ДА"
    private final By yesOrderButton = By.xpath(".//button[text()='Да']");

    //заголовок Заказ оформлен
    private final By orderOk = By.className("Order_ModalHeader__3FDaJ");

    //текст заголовка Заказ оформлен
    private final String textOrderOk= "Заказ оформлен";

    //логотип Самоката
    private final By lofoOfScooter = By.className("Header_LogoScooter__3lsAR");

    //сообщение об ошибке в имени
    private final By wrongName = By.xpath(".//div[text()='Введите корректное имя']");

    //сообщение об ошибке в фамилии
    private final By wrongSurname = By.xpath(".//div[text()='Введите корректную фамилию']");

    //сообщение об ошибке в адресе
    private final By wrongAddress = By.xpath(".//div[text()='Введите корректный адрес']");

    //сообщение об ошибке в станции
    private final By wrongStation = By.xpath(".//div[text()='Выберите станцию']");

    //сообщение об ошибке в номере телефона
    private final By wrongPhoneNumber = By.xpath(".//div[text()='Введите корректный номер']");


    public void fillInInfoAboutYourselfWithData(String name, String surname, String address, String station, String phoneNumber) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);                           //заполнить имя

        driver.findElement(surnameInput).clear();
        driver.findElement(surnameInput).sendKeys(surname);                     //заполнить фамилию

        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);                     //заполнить адрес

        driver.findElement(stationInput).clear();
        driver.findElement(stationInput).click();
        String locator = String.format(stationChoose,station);
        boolean isExist = !driver.findElements(By.xpath(locator)).isEmpty();
        if (isExist) {
            WebElement element = driver.findElement(By.xpath(locator));
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
            driver.findElement(By.xpath(locator)).click();                      //выбрать станцию
        }

        driver.findElement(phoneNumberInput).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);             // заполнить телефон
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();                                 //нажать кнопку Далее
    }

    public void fillInInfoAboutRentWithData(LocalDate date, String period, boolean blackScooter, boolean greyScooter, String comment) {

        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        driver.findElement(dateInput).clear();
        driver.findElement(dateInput).sendKeys(date.toString());                // выбрать дату
        driver.findElement(dateInput).sendKeys(Keys.ESCAPE);

        WebElement dropDown = driver.findElement(periodOfRentInput);
        dropDown.click();
        String locator = String.format(periodOfRentChoose, period);
        boolean isExist = !driver.findElements(By.xpath(locator)).isEmpty();
        if (isExist) {
            driver.findElement(By.xpath(locator)).click();                      // выбрать срок аренды
        }

        if (blackScooter) {
            driver.findElement(colourBlackCheckbox).click();                    // выбрать цвет скутера
        }

        if (greyScooter) {
            driver.findElement(colourGreyCheckbox).click();
        }

        driver.findElement(commentInput).clear();                               // добавить комменты
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButton).click();                                // клик Заказать
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(yesOrderButton));
        driver.findElement(yesOrderButton).click();                             // клик Ок
    }

    public boolean isHeaderOrderOkIsDisplayed () {
        return driver.findElement(orderOk).getText().contains(textOrderOk);
    }

    public MainPage goToPreviousPage () {
        driver.navigate().back();
        return new MainPage(driver);
    }

    public boolean isOrderPageIsVisible() {
        return driver.findElement(nameInput).isDisplayed();
    }

    public void clickOnLogoScooter () {
        driver.findElement(lofoOfScooter).click();
    }

    public boolean isMessageWrongNameIsDisplayed() {
        return driver.findElement(wrongName).isDisplayed();
    }

    public boolean isMessageWrongSurnameIsDisplayed() {
        return driver.findElement(wrongSurname).isDisplayed();
    }

    public boolean isMessageWrongAddressIsDisplayed() {
        return driver.findElement(wrongAddress).isDisplayed();
    }

    public boolean isMessageWrongStationIsDisplayed() {
        return driver.findElement(wrongStation).isDisplayed();
    }

    public boolean isMessageWrongPhoneNumberIsDisplayed() {
        return driver.findElement(wrongPhoneNumber).isDisplayed();
    }

}
