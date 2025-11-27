package Lesson_4;

public abstract class Animal {
    private static int animalCount = 0;
    private String name;
    private int maxRunLength = 0;
    private int maxSwimLength = 0;

    public Animal(String name) {
        this.name = name;
        animalCount ++;
    }

    public void run(int len) {
        if (len <= 0) {
            System.out.println("Ошибка ввода: Животное не может пробежать отрицательное растояние.");
            return;
        }

        if (len <= maxRunLength)
            System.out.printf("%s пробежал(а) %d м.\n", name, len);
        else
            System.out.printf("Дистанция слишком велика. %s не смог(ла) ее преодолеть.\n", name);
    }

    public void swim(int len) {
        if (len <= 0) {
            System.out.println("Ошибка ввода: Животное не может проплыть отрицательное растояние.");
            return;
        }

        if (maxSwimLength == 0){
            System.out.printf("%s не умеет плавать.\n", name);
            return;
        }

        if (len <= maxSwimLength)
            System.out.printf("%s проплыл(а) %d м.\n", name, len);
        else
            System.out.printf("Дистанция слишком велика. %s не смог(ла) ее преодолеть.\n", name);
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }

    public void setMaxRunLength(int maxRunLength) {
        this.maxRunLength = maxRunLength;
    }

    public void setMaxSwimLength(int maxSwimLength) {
        this.maxSwimLength = maxSwimLength;
    }
}
