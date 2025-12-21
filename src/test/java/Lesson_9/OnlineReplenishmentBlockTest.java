package test.java.Lesson_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @ParameterizedTest
    @CsvSource({
            "Visa",
            "Verified By Visa",
            "MasterCard",
            "MasterCard Secure Code",
            "Белкарт"
    })
    public void presenceOfPaymentSystemLogosTest(String logoAlt) {
        WebElement logo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='" + logoAlt + "']"));
        assertAll(
                () -> assertTrue(logo.isDisplayed(), "Логотип не отображается"),
                () -> assertNotNull(logo.getAttribute("src"), "Отсутствует атрибут src"),
                () -> assertFalse(logo.getAttribute("src").isEmpty(), "Атрибут src пуст")
        );
    }

//    Проверить работу ссылки «Подробнее о сервисе»;
    @Test
    public void linkWorksTest() {
        String link = driver.findElement(By.xpath("//div[@class='pay__wrapper']//a")).getAttribute("href");

        given().
                baseUri("https://www.mts.by/")
                .log().all()
        .when()
                .get(link)
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

        String framePath = "//iframe[@class='bepaid-iframe']";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(framePath))));

        String pageSourse = driver.getPageSource();
        assertTrue(pageSourse.contains("<title>BePaidWidget</title>"));

//      Так и не разобралась как работает переход во фрейм.
//      driver.getPageSource() возвращает код фрейма с <title>BePaidWidget</title>
//      и пустым app-root (он должен содержать элементы фрейма)
//      при этом driver.getTitle() возвращает title главной страницы как будто перехода не было
//      При попытке найти title фрейма текст элемента пустой
//
//        System.out.println(pageSourse);
//        System.out.println(driver.getTitle());
//        System.out.println(driver.findElement(By.xpath("//html[@class='notranslate']//title")).getText());
    }
}
