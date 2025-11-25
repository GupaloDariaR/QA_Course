package Lesson_3;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Product p = new Product(
                "Электрочайник Smeg KLF05CREU",
                LocalDate.parse("2024-11-25"),
                "Smeg",
                "Китай",
                1789f,
                true
        );
        p.printInfo();
    }
}
