package Lesson_4;

public class App {
    public static void main(String[] args) {
//      Задание 1 (часть 1)
        System.out.println("Задание 1 (часть 1)\n");
        Cat cat = new Cat("Мурзик");
        Dog dog = new Dog("Шустрик");
//      test cat
        cat.run(-1);
        cat.run(0);
        cat.run(200);
        cat.run(201);
        cat.swim(1);
        System.out.println();

//      test dog
        dog.run(500);
        dog.run(501);
        dog.swim(-1);
        dog.swim(0);
        dog.swim(10);
        dog.swim(11);
        System.out.println();

//      print count
        printAnimalCount();

//      Задание 1 (часть 2)
        System.out.println("\n\nЗадание 1 (часть 2)\n");
//      test method cat.eat()
        Cat[] cats = new Cat[] {
          new Cat("Барсик"),
          new Cat("Муся"),
          new Cat("Вася"),
          new Cat("Белка"),
          new Cat("Снежок"),
        };

        int bowl = 50;
        for (Cat c: cats) {
            bowl = c.eat(bowl, 15);
            System.out.printf("Сытость %s - %b\n",c.getName(), c.getSatiety());
        }

        System.out.println("\nЕды в миске: " + bowl);
        bowl = addFoodToBowl(bowl, 100);
        System.out.println("Еды в миске: " + bowl);

//      print count
        System.out.println();
        printAnimalCount();


//      Задание 2
        System.out.println("\n\nЗадание 2\n");
        Circle circle = new Circle(new int[] {1}, "red", "black");
        Rectangle rectangle = new Rectangle(new int[] {1, 2}, "blue", "black");
        Triangle triangle = new Triangle(new int[] {6, 8, 10}, "green", "black");

        circle.getInfo();
        rectangle.getInfo();
        triangle.getInfo();
    }

    public static void printAnimalCount() {
        System.out.println("Animal count = " + Animal.getAnimalCount());
        System.out.println("Dog count = " + Dog.getDogCount());
        System.out.println("Cat count = " + Cat.getCatCount());
    }

    public static int addFoodToBowl(int bowl, int countFood) {
        System.out.println("Добавление еды в миску...");
        return bowl + countFood;
    }
}
