package test.java.Lesson_10;

import org.openqa.selenium.By;
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
        this.driver = driver;
        if (!getBlockTitle().equals("Онлайн пополнение без комиссии"))
            throw new IllegalStateException("Эта страница не содержит блок онлайн пополнения");
    }

    public void acceptCookie() {
        driver.findElement(cookieLocator).click();
    }

    public String getBlockTitle() {
        return driver.findElement(blockTitleLocator)
                .getText().replace('\n',' ');
    }

    public WebElement getLogoByALt(String alt) {
        return driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='" + alt + "']"));
    }

    public String getLinksAttributeHref() {
        return driver.findElement(linkLocator).getAttribute("href");
    }

    private void typePhone(String phone) {
        WebElement phoneInputField = driver.findElement(phoneInputFieldLocator);
        phoneInputField.click();
        phoneInputField.sendKeys(phone);
    }

    private void typeSum(String sum) {
        WebElement sumInputField = driver.findElement(sumInputFieldLocator);
        sumInputField.click();
        sumInputField.sendKeys(sum);
    }

    private PayPage submitOnlineReplenishment() {
        driver.findElement(buttonLocator).submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator)));
        return new PayPage(driver);
    }

    public PayPage onlineReplenishmentOn(String phone, String sum) {
        typePhone(phone);
        typeSum(sum);
        return submitOnlineReplenishment();
    }
}
