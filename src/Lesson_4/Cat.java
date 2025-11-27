package Lesson_4;

public class Cat extends Animal{
    private static int catCount = 0;

    public Cat(String name) {
        super(name);
        super.setMaxRunLength(200);
        super.setMaxSwimLength(0);
        catCount ++;
    }

    public static int getCatCount() {
        return catCount;
    }
}
