package test.java.Lesson_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    @ParameterizedTest
    @CsvSource({"297777777, 10"})
    public void buttonWorksTest(String phone, String sum) {
        assertTrue(onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum).isTitleExists());
    }

//    Проверить надписи в незаполненных полях каждого варианта оплаты услуг:
//    услуги связи, домашний интернет, рассрочка, задолженность
    @ParameterizedTest
    @CsvSource({
            "connection, Номер телефона",
            "internet, Номер абонента",
            "instalment, Номер счета на 44",
            "arrears, Номер счета на 2073 "
    })
    public void presenceOfPlaceholdersTest(String formId, String phoneOrScorePlaceholder) {
        assertAll(
                () -> assertEquals(phoneOrScorePlaceholder,
                        onlineReplenishmentBlock.getPlaceholderOfPhoneOrScoreField(formId)),
                () -> assertEquals("Сумма",
                        onlineReplenishmentBlock.getPlaceholderOfSumField(formId)),
                () -> assertEquals("E-mail для отправки чека",
                        onlineReplenishmentBlock.getPlaceholderOfEmailField(formId))
        );
    }

//    Проверить корректность отображения суммы (в том числе на кнопке) в окне оплаты
    @ParameterizedTest
    @CsvSource({"297777777, 10"})
    public void presenceOfSum(String phone, String sum) {
        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        List<String> listOfText = payPage.getTextFromSumElements();
        assertAll(
                () -> assertTrue(listOfText.get(0).contains(sum)),
                () -> assertTrue(listOfText.get(1).contains(sum)),
                () -> assertTrue(listOfText.get(2).contains(sum))
        );
    }

//    Проверить корректность отображения номера телефона в окне оплаты
    @ParameterizedTest
    @CsvSource({"297777777, 10"})
    public void presenceOfPhone(String phone, String sum) {
        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        String text = payPage.getTextFromPhoneElement();
        assertTrue(text.contains(phone));
    }

//    Проверить корректность отображения надписей в незаполненных полях для ввода реквизитов карты в окне оплаты
    @ParameterizedTest
    @CsvSource({"297777777, 10"})
    public void presenceOfPlaceholdersInPayPageTest (String phone, String sum) {
        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        String labelOfCardNumberField = payPage.getLabelOfCardNumberField();
        String labelOfValidityPeriodField = payPage.getLabelOfValidityPeriodField();
        String labelOfCvcField = payPage.getLabelOfCvcField();
        String labelOfNameField = payPage.getLabelOfNameField();

        assertAll(
                () -> assertEquals("Номер карты", labelOfCardNumberField),
                () -> assertEquals("Срок действия", labelOfValidityPeriodField),
                () -> assertEquals("CVC", labelOfCvcField),
                () -> assertEquals("Имя и фамилия на карте", labelOfNameField)
        );
    }

//    Проверить наличие иконок платёжных систем в окне оплаты
    @ParameterizedTest
    @CsvSource({"297777777, 10"})
    public void presenceOfIconsOfPaymentSystemTest(String phone, String sum) {
        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        List<WebElement> icons = payPage.getiIconsOfPaymentSystem();
        Collection<Executable> executables = new ArrayList<>();
        for (WebElement icon: icons) {
            executables.add(() -> assertNotNull(icon.getAttribute("src"), "Отсутствует атрибут src"));
            executables.add(() -> assertFalse(icon.getAttribute("src").isEmpty(), "Атрибут src пуст"));
        }
        assertAll(executables);
    }

}
