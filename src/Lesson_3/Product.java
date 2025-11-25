package Lesson_3;

import java.time.LocalDate;

public class Product {
    private String title;
    private LocalDate dateOfProduction;
    private String producer;
    private String country;
    private float price;
    private boolean statusOfBooking;

    public Product (String title, LocalDate dateOfProduction, String producer,
                    String country, float price, boolean statusOfBooking) {
        this.title = title;
        this.dateOfProduction = dateOfProduction;
        this.producer = producer;
        this.country = country;
        this.price = price;
        this.statusOfBooking = statusOfBooking;
    }

    public void printInfo() {
        System.out.printf("""
            Название: %s
            Дата производства: %tF
            Производитель: %s
            Страна происхождения: %s
            Цена: %.2f
            Состояние бронирования покупателем:\s""",
            title, dateOfProduction, producer, country, price);

        if (statusOfBooking) System.out.println("Товар забронирован");
        else System.out.println("Брони нет");
    }
}
