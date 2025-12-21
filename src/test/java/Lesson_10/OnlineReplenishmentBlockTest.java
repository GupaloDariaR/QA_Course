package test.java.Lesson_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class OnlineReplenishmentBlockTest {

    private WebDriver driver;
    OnlineReplenishmentBlock onlineReplenishmentBlock;
    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        onlineReplenishmentBlock = new OnlineReplenishmentBlock(driver);
//      закрываем окно "Обработка файлов cookie"
        onlineReplenishmentBlock.acceptCookie();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //    Проверить название указанного блока;
    @Test
    public void blockTitleTest() {
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        String actualBlockTitle = onlineReplenishmentBlock.getBlockTitle();
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
        WebElement logo = onlineReplenishmentBlock.getLogoByALt(logoAlt);
        assertAll(
                () -> assertTrue(logo.isDisplayed(), "Логотип не отображается"),
                () -> assertNotNull(logo.getAttribute("src"), "Отсутствует атрибут src"),
                () -> assertFalse(logo.getAttribute("src").isEmpty(), "Атрибут src пуст")
        );
    }

    //    Проверить работу ссылки «Подробнее о сервисе»;
    @Test
    public void linkWorksTest() {
        String link = onlineReplenishmentBlock.getLinksAttributeHref();

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

        assertTrue(onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum).isTitleExists());
    }
}
