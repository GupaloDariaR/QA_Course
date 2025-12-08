package Lesson_6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class App {
    public static void main(String[] args) {
//      инициализация
        HashSet<Student> students = new HashSet<>();

        try {
            students.add(new Student("Иванов", "Иван", "бсбо-10-25", 1,
                            new HashMap<>(Map.of(
                                    "Русский язык", 3,
                                    "Математика", 3,
                                    "Информатика", 3))
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            students.add(new Student("Никитин", "Никита", "бсбо-10-23", 3,
                            new HashMap<>(Map.of(
                                    "Русский язык", 3,
                                    "Математика", 5,
                                    "Информатика", 4))
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            students.add(new Student("Петров", "Петр", "бсбо-10-22", 4,
                            new HashMap<>(Map.of(
                                    "Русский язык", 5,
                                    "Математика", 5,
                                    "Информатика", 5))
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            students.add(new Student("Васечкин", "Василий", "бсбо-10-24", 2,
                            new HashMap<>(Map.of(
                                    "Русский язык", 2,
                                    "Математика", 3,
                                    "Информатика", 3))
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

//      удаление студента со средним баллом < 3
        removeStudent(students);
        System.out.println();

//      перевод на следующий курс
        for (Student s: students) {
            s.transferToNextCourse();
            System.out.println(s.getName() + " на " + s.getCourse() + " курсе");
        }

//      печать студентов на 4 курсе
        System.out.println("\nСтуденты на 4 курсе:");
        printStudents(students, 4);

    }

    public static void removeStudent(HashSet<Student> students) {
        students.removeIf(s -> {
            if (s.getAverageScore() < 3){
                System.out.println("Удален студент " + s.getName());
                return true;
            }
            return false;
        });
    }

    public static void printStudents(HashSet<Student> students, int course) {
        for (Student s: students) {
            if (s.getCourse() == course)
                System.out.println(s.getName());
        }
    }

}






