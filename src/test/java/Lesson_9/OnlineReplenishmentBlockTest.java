package test.java.Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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

//      закрываем окно "Обработка файлов cookie"
        driver.findElement(By.xpath("//button[@id='cookie-agree']")).click();
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
        WebElement link = driver.findElement(By.xpath("//div[@class='pay__wrapper']//a"));

        given().
                baseUri("https://www.mts.by/")
                .log().all()
        .when()
                .get(link.getAttribute("href"))
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("html.head.title", equalTo("Порядок оплаты и безопасность интернет платежей"));
    }

//    Заполнить поля и проверить работу кнопки «Продолжить»
//    (проверяем только вариант «Услуги связи», номер для теста 297777777)
    @Test
    public void buttonWorksTest() {
        String phone = "297777777";
        String sum = "10";

        WebElement phoneInputField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        WebElement sumInputField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        WebElement button = driver.findElement(By.xpath("//form[@id='pay-connection']//button"));

        phoneInputField.click();
        phoneInputField.sendKeys(phone);

        sumInputField.click();
        sumInputField.sendKeys(sum);

        button.click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']"));
        driver.switchTo().frame(iframe);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assertAll(
                () -> assertTrue(driver.findElement(By.xpath("//app-payment-container")).isDisplayed()),
                () -> assertTrue(driver.findElement(
                        By.xpath("//app-root//span[contains(text(), 'Номер')]"))
                        .getText().contains(phone)),
                () -> assertTrue(driver.findElement(
                        By.xpath("//app-root//span[@class='ng-star-inserted']"))
                        .getText().contains(sum))
        );
    }
}
