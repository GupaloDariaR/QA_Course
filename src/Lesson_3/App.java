package Lesson_3;

import java.time.LocalDate;
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {
//      Задание 2
        System.out.println("Задание 2\n");

        Product[] products = new Product[] {
                new Product(
                        "Электрочайник Smeg KLF05CREU",
                        LocalDate.parse("2024-11-25"),
                        "Smeg",
                        "Китай",
                        12729f,
                        true
                ),
                new Product(
                        "Шуруповерт аккумуляторный OASIS ECO ASB-12S (J)",
                        LocalDate.parse("2024-05-22"),
                        "OASIS",
                        "Китай",
                        1709f,
                        false
                ),
                new Product(
                        "Смартфон Samsung Galaxy Flip7",
                        LocalDate.parse("2025-10-12"),
                        "Samsung",
                        "Вьетнам",
                        91999f,
                        false
                ),
                new Product(
                        "Телевизор Sber SDX-43UQ6032",
                        LocalDate.parse("2025-09-19"),
                        "Sber",
                        "Россия",
                        22999f,
                        true
                ),
                new Product(
                        "Электрогриль Haier HG-701",
                        LocalDate.parse("2024-12-07"),
                        "Haier",
                        "Китай",
                        24999f,
                        false
                ),
        };

        for (Product product: products) {
            product.printInfo();
        }

//      Задание 3
        System.out.println("Задание 3\n");

        Park park = new Park(
                new Park().new Attraction(
                        "Молот судьбы",
                        LocalTime.parse("10:00:00"),
                        LocalTime.parse("22:00:00"),
                        2100f
                ),
                new Park().new Attraction(
                        "Гонка будущего",
                        LocalTime.parse("09:00:00"),
                        LocalTime.parse("19:00:00"),
                        3000f
                )
        );
        park.getAttraction1().printInfo();
        park.getAttraction2().printInfo();
    }
}
