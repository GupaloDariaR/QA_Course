package Lesson_3;

import java.time.LocalTime;

public class Park {
    private Attraction attraction1;
    private Attraction attraction2;

    public Park() {}
    public Park (Attraction attraction1, Attraction attraction2) {
        this.attraction1 = attraction1;
        this.attraction2 = attraction2;
    }

    public class Attraction {
        private String name;
        private LocalTime startTimeOfWork;
        private LocalTime endTimeOfWork;
        private float price;

        public Attraction(String name, LocalTime startTimeOfWork,
                          LocalTime endTimeOfWork, float price) {
            this.name = name;
            this.startTimeOfWork = startTimeOfWork;
            this.endTimeOfWork = endTimeOfWork;
            this.price = price;
        }

        public void printInfo() {
            System.out.printf("""
                Название аттракциона: %s
                Время работы: %tR - %tR
                Стоимость билета: %.2f руб.
                
                """,
                name, startTimeOfWork, endTimeOfWork, price);
        }
    }

    public Attraction getAttraction1() {return attraction1;}
    public Attraction getAttraction2() {return attraction2;}
}
