package Lesson_6;

public class App2 {
    public static void main(String[] args) {
        PhoneGuide phoneGuide = new PhoneGuide();
        phoneGuide.add("+79005009070", "Иванов");
        phoneGuide.add("+79174521232", "Иванов");
        phoneGuide.add("+79159875467", "Петров");

        System.out.println(phoneGuide.get("Иванов"));
        System.out.println(phoneGuide.get("Петров"));
    }
}
