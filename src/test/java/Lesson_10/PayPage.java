package test.java.Lesson_10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PayPage {

    private final WebDriver driver;

    public PayPage(WebDriver driver) {
        this.driver = driver;
        if (!isTitleExists())
            throw new IllegalStateException("Это не страница оплаты");
    }

    public Boolean isTitleExists() {
        return driver.getPageSource().contains("<title>BePaidWidget</title>");
    }

    public String getTextFromPhoneElement() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//app-root//span[contains(text(), 'Номер')]";
                const phoneElement = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                return phoneElement.textContent;
                """));
        return (String) text;
    }

    public List<String> getTextFromSumElements() {
        List<String> listOfText = new ArrayList<>();
        listOfText.add((String)
                new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                    const xpath = "//app-root//span[@class='ng-star-inserted']";
                    const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                    return el.textContent;
                """)));

        listOfText.add((String)
                new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                    const xpath = "//div[@class='card-page__card']//button";
                    const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                    return el.textContent;
                """)));

        listOfText.add((String)
                new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                    const xpath = "//div[contains(@class, 'card-page__agreement')]//span";
                    const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                    return el.textContent;
                """)));

        return listOfText;
    }

    public String getLabelOfCardNumberField() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//label[contains(@class, 'ng-tns-c2312288139-2')]";
                const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                return el.textContent;
                """));
        return (String) text;
    }

    public String getLabelOfValidityPeriodField() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//label[contains(@class, 'ng-tns-c2312288139-4')]";
                const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                return el.textContent;
                """));
        return (String) text;
    }

    public String getLabelOfCvcField() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//label[contains(@class, 'ng-tns-c2312288139-5')]";
                const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                return el.textContent;
                """));
        return (String) text;
    }

    public String getLabelOfNameField() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//label[contains(@class, 'ng-tns-c2312288139-3')]";
                const el = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
                return el.textContent;
                """));
        return (String) text;
    }

    public List<WebElement> getiIconsOfPaymentSystem() {
        Object text = new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("""
                const xpath = "//div[@class='icons-container ng-tns-c2312288139-2']//img";
                const imgs = document.evaluate(xpath, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
                const elementsArray = [];
                for (let i = 0; i < imgs.snapshotLength; i++) {
                    elementsArray.push(imgs.snapshotItem(i));
                }
                return elementsArray;
                """));
        return (List<WebElement>) text;
    }
}
