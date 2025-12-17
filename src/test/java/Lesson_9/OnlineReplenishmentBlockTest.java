package test.java.Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class OnlineReplenishmentBlockTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

//    Проверить название указанного блока;
    @Test
    public void blockTitleTest() {
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        String actualBlockTitle = driver.findElement(
                By.xpath("//div[@class='pay__wrapper']/h2"))
                .getText().replace('\n',' ');
        assertEquals(expectedBlockTitle, actualBlockTitle);
    }

//    Проверить наличие логотипов платёжных систем;
    @Test
    public void presenceOfPaymentSystemLogosTest() {
       WebElement imgVisa = driver.findElement(
               By.xpath("//div[@class='pay__partners']//img[@alt='Visa']"));
       WebElement imgVerifiedByVisa = driver.findElement(
               By.xpath("//div[@class='pay__partners']//img[@alt='Verified By Visa']"));
       WebElement imgMasterCard = driver.findElement(
               By.xpath("//div[@class='pay__partners']//img[@alt='MasterCard']"));
       WebElement imgMasterCardSecureCode = driver.findElement(
               By.xpath("//div[@class='pay__partners']//img[@alt='MasterCard Secure Code']"));
       WebElement imgBelcard = driver.findElement(
               By.xpath("//div[@class='pay__partners']//img[@alt='Белкарт']"));

       String notDisplayedMessage = "Логотип не отображается";
       String nullMessage = "Отсутствует атрибут src";
       String emptyMessage = "Атрибут src пуст";

       assertAll(
               () -> assertTrue(imgVisa.isDisplayed(), notDisplayedMessage),
               () -> assertNotNull(imgVisa.getAttribute("src"), nullMessage),
               () -> assertFalse(imgVisa.getAttribute("src").isEmpty(), emptyMessage),

               () -> assertTrue(imgVerifiedByVisa.isDisplayed(), notDisplayedMessage),
               () -> assertNotNull(imgVerifiedByVisa.getAttribute("src"), nullMessage),
               () -> assertFalse(imgVerifiedByVisa.getAttribute("src").isEmpty(), emptyMessage),

               () -> assertTrue(imgMasterCard.isDisplayed(), notDisplayedMessage),
               () -> assertNotNull(imgMasterCard.getAttribute("src"), nullMessage),
               () -> assertFalse(imgMasterCard.getAttribute("src").isEmpty(), emptyMessage),

               () -> assertTrue(imgMasterCardSecureCode.isDisplayed(), notDisplayedMessage),
               () -> assertNotNull(imgMasterCardSecureCode.getAttribute("src"), nullMessage),
               () -> assertFalse(imgMasterCardSecureCode.getAttribute("src").isEmpty(), emptyMessage),

               () -> assertTrue(imgBelcard.isDisplayed(), notDisplayedMessage),
               () -> assertNotNull(imgBelcard.getAttribute("src"), nullMessage),
               () -> assertFalse(imgBelcard.getAttribute("src").isEmpty(), emptyMessage)
       );
    }

//    Проверить работу ссылки «Подробнее о сервисе»;
    @Test
    public void linkWorksTest() {
    }

//    Заполнить поля и проверить работу кнопки «Продолжить»
//    (проверяем только вариант «Услуги связи», номер для теста 297777777)
//    @Test
//    public void buttonWorksTest() {
//
//    }
}
