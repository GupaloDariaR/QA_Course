package test.java.Lesson_10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
