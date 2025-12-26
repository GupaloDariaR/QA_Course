package test.java.Lesson_11.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.Lesson_11.pages.OnlineReplenishmentBlock;
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

    @Test
    @DisplayName("Название блока соответствует \"Онлайн пополнение без комиссии\"")
    @Story("Блок \"Онлайн пополнение без комиссии\"")
    public void blockTitleTest() {
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        String actualBlockTitle = onlineReplenishmentBlock.getBlockTitle();
        assertEquals(expectedBlockTitle, actualBlockTitle);
    }

    @ParameterizedTest
    @DisplayName("Наличие логотипов платёжных систем")
    @Story("Блок \"Онлайн пополнение без комиссии\"")
    @CsvSource({
            "Visa",
            "Verified By Visa",
            "MasterCard",
            "MasterCard Secure Code",
            "Белкарт"
    })
    public void presenceOfPaymentSystemLogosTest(String logoAlt) {
        Allure.parameter("logoAlt", logoAlt);

        WebElement logo = onlineReplenishmentBlock.getLogoByALt(logoAlt);
        assertAll(
                () -> assertTrue(logo.isDisplayed(), "Логотип не отображается"),
                () -> assertNotNull(logo.getAttribute("src"), "Отсутствует атрибут src"),
                () -> assertFalse(logo.getAttribute("src").isEmpty(), "Атрибут src пуст")
        );
    }

    @Test
    @DisplayName("Работа ссылки \"Подробнее о сервисе\"")
    @Description("При нажатии на ссылку \"Подробнее о сервисе\" открывается страница с " +
            "названием \"Порядок оплаты и безопасность интернет платежей\"")
    @Story("Блок \"Онлайн пополнение без комиссии\"")
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

    @ParameterizedTest
    @DisplayName("Работа кнопки \"Продолжить\"")
    @Description("После заполнения формы и нажатия кнопки «Продолжить» " +
            "открывается окно с названием BePaidWidget")
    @Story("Блок \"Онлайн пополнение без комиссии\"")
    @CsvSource({"297777777, 10"})
    public void buttonWorksTest(String phone, String sum) {
        Allure.parameter("phone", phone);
        Allure.parameter("sum", sum);

        assertTrue(onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum).isTitleExists());
    }

    @ParameterizedTest
    @DisplayName("Наличие надписей в незаполненных полях каждого " +
            "варианта оплаты услуг")
    @Story("Блок \"Онлайн пополнение без комиссии\"")
    @CsvSource({
            "connection, Номер телефона",
            "internet, Номер абонента",
            "instalment, Номер счета на 44",
            "arrears, Номер счета на 2073 "
    })
    public void presenceOfPlaceholdersTest(String formId, String phoneOrScorePlaceholder) {
        Allure.parameter("formId", formId);
        Allure.parameter("phoneOrScorePlaceholder", phoneOrScorePlaceholder);

        assertAll(
                () -> assertEquals(phoneOrScorePlaceholder,
                        onlineReplenishmentBlock.getPlaceholderOfPhoneOrScoreField(formId)),
                () -> assertEquals("Сумма",
                        onlineReplenishmentBlock.getPlaceholderOfSumField(formId)),
                () -> assertEquals("E-mail для отправки чека",
                        onlineReplenishmentBlock.getPlaceholderOfEmailField(formId))
        );
    }
}
