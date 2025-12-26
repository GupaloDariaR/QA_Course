package test.java.Lesson_11.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.Lesson_11.pages.OnlineReplenishmentBlock;
import main.java.Lesson_11.pages.PayPage;
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

import static org.junit.jupiter.api.Assertions.*;

public class PayPageTest {

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

    @ParameterizedTest
    @DisplayName("Корректность отображения суммы (в том числе на кнопке) в окне оплаты")
    @Description("Сумма, отображающаяся в окне оплаты, должна совпадать с " +
            "суммой, введенной в поле блока \"Онлайн пополнение без комиссии\"")
    @Story("Окно оплаты")
    @CsvSource({"297777777, 10"})
    public void presenceOfSum(String phone, String sum) {
        Allure.parameter("phone", phone);
        Allure.parameter("sum", sum);

        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        List<String> listOfText = payPage.getTextFromSumElements();
        assertAll(
                () -> assertTrue(listOfText.get(0).contains(sum)),
                () -> assertTrue(listOfText.get(1).contains(sum)),
                () -> assertTrue(listOfText.get(2).contains(sum))
        );
    }

    @ParameterizedTest
    @DisplayName("Корректность отображения номера телефона в окне оплаты")
    @Description("Телефон, отображающийся в окне оплаты, должен совпадать с " +
            "телефоном, введенным в поле блока \"Онлайн пополнение без комиссии\"")
    @Story("Окно оплаты")
    @CsvSource({"297777777, 10"})
    public void presenceOfPhone(String phone, String sum) {
        Allure.parameter("phone", phone);
        Allure.parameter("sum", sum);

        PayPage payPage = onlineReplenishmentBlock.onlineReplenishmentOn(phone, sum);
        String text = payPage.getTextFromPhoneElement();
        assertTrue(text.contains(phone));
    }

    @ParameterizedTest
    @DisplayName("Корректность отображения надписей в незаполненных полях " +
            "для ввода реквизитов карты в окне оплаты")
    @Story("Окно оплаты")
    @CsvSource({"297777777, 10"})
    public void presenceOfPlaceholdersInPayPageTest (String phone, String sum) {
        Allure.parameter("phone", phone);
        Allure.parameter("sum", sum);

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

    @ParameterizedTest
    @DisplayName("Наличие иконок платёжных систем в окне оплаты")
    @Story("Окно оплаты")
    @CsvSource({"297777777, 10"})
    public void presenceOfIconsOfPaymentSystemTest(String phone, String sum) {
        Allure.parameter("phone", phone);
        Allure.parameter("sum", sum);

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
