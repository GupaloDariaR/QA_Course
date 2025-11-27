package Lesson_4;

public class Dog extends Animal{
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        super.setMaxRunLength(500);
        super.setMaxSwimLength(10);
        dogCount ++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
