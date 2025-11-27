package Lesson_4;

public class Cat extends Animal{
    private static int catCount = 0;
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
        super.setMaxRunLength(200);
        super.setMaxSwimLength(0);
        catCount ++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public int eat(int bowl, int foodCountForCat) {
        int bowlRemains = bowl - foodCountForCat;
        if (bowlRemains < 0) return bowl;
        satiety = true;
        return bowlRemains;
    }
}
