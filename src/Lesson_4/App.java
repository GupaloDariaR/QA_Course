package Lesson_4;

public class App {
    public static void main(String[] args) {
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
        System.out.println("Animal count = " + Animal.getAnimalCount());
        System.out.println("Dog count = " + Dog.getDogCount());
        System.out.println("Cat count = " + Cat.getCatCount());
    }
}
