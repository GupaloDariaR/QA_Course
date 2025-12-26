package main.java.Lesson_11.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineReplenishmentBlock {

    private final WebDriver driver;
    private final By cookieLocator = By.xpath("//button[@id='cookie-agree']");
    private final By blockTitleLocator = By.xpath("//div[@class='pay__wrapper']/h2");
    private final By linkLocator = By.xpath("//div[@class='pay__wrapper']//a");
    private final By phoneInputFieldLocator = By.xpath("//input[@id='connection-phone']");
    private final By sumInputFieldLocator = By.xpath("//input[@id='connection-sum']");
    private final By buttonLocator = By.xpath("//form[@id='pay-connection']//button");
    private final By iframeLocator = By.xpath("//iframe[@class='bepaid-iframe']");

    public OnlineReplenishmentBlock(WebDriver driver) {
        Allure.step("Создание объекта класса OnlineReplenishmentBlock");

        this.driver = driver;
        if (!getBlockTitle().equals("Онлайн пополнение без комиссии"))
            throw new IllegalStateException("Эта страница не содержит блок \"Онлайн пополнение без комиссии\"");
    }

    public void acceptCookie() {
        Allure.step("Принятие Cookie");

        driver.findElement(cookieLocator).click();
    }

    public String getBlockTitle() {
        Allure.step("Получение названия блока");

        return driver.findElement(blockTitleLocator)
                .getText().replace('\n',' ');
    }

    public WebElement getLogoByALt(String alt) {
        Allure.step("Получение логотипа платежной системы по его атрибуту alt");

        return driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='" + alt + "']"));
    }

    public String getLinksAttributeHref() {
        Allure.step("Получение атрибута href ссылки");

        return driver.findElement(linkLocator).getAttribute("href");
    }

    private void typePhone(String phone) {
        Allure.step("Введение телефона в поле \"Номер телефона\"");

        WebElement phoneInputField = driver.findElement(phoneInputFieldLocator);
        phoneInputField.click();
        phoneInputField.sendKeys(phone);
    }

    private void typeSum(String sum) {
        Allure.step("Введение суммы в поле \"Сумма\"");

        WebElement sumInputField = driver.findElement(sumInputFieldLocator);
        sumInputField.click();
        sumInputField.sendKeys(sum);
    }

    private PayPage submitOnlineReplenishment() {
        Allure.step("Нажатие на кнопку \"Продолжить\". Переход к окно оплаты");

        driver.findElement(buttonLocator).submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator)));
        return new PayPage(driver);
    }

    public PayPage onlineReplenishmentOn(String phone, String sum) {
        Allure.step("Заполнение формы");

        typePhone(phone);
        typeSum(sum);
        return submitOnlineReplenishment();
    }

    public String getPlaceholderOfPhoneOrScoreField(String formId) {
        Allure.step("Получение плейсхолдера первого поля формы");

        WebElement field;
        try {
            field = driver.findElement(By.xpath("//input[@id='" + formId + "-phone']"));
        } catch (NoSuchElementException e) {
            field = driver.findElement(By.xpath("//input[@id='score-" + formId + "']"));
        }
        return field.getAttribute("placeholder");
    }

    public String getPlaceholderOfSumField(String formId) {
        Allure.step("Получение плейсхолдера второго поля формы");

        WebElement field;
        field = driver.findElement(By.xpath("//input[@id='" + formId + "-sum']"));
        return field.getAttribute("placeholder");
    }

    public String getPlaceholderOfEmailField(String formId) {
        Allure.step("Получение плейсхолдера третьего поля формы");

        WebElement field;
        field = driver.findElement(By.xpath("//input[@id='" + formId + "-email']"));
        return field.getAttribute("placeholder");
    }
}