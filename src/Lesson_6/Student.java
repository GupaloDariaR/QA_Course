package Lesson_6;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String lastName;
    private String firstName;
    private String group;
    private int course;
    private HashMap<String, Integer> scores;

    public Student(String lastName, String firstName, String group, int course, HashMap<String, Integer> scores) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;

        if (checkCourse(course))
            this.course = course;

        if (checkScores(scores))
            this.scores = scores;
    }

    private boolean  checkCourse (int course) {
        if (course > 4 || course < 1)
            throw new IllegalArgumentException("Задано неверное значение курса: поле course принимает значения от 1 до 4");
        return true;
    }

    private boolean checkScores(HashMap<String, Integer> scores) {
        if (scores.isEmpty())
            throw new IllegalArgumentException("Задано неверное значение поля scores: не может быть пустым");

        for (Map.Entry<String, Integer> s: scores.entrySet()) {
            if (s.getValue() < 1 || s.getValue() > 5)
                throw new IllegalArgumentException("Задано неверное значение поля scores: оценка (value) примимает зачения от 1 до 5");
            break;
        }

        return true;
    }

    public float getAverageScore() {
        int sumOfScores = 0;
        for(Map.Entry<String, Integer> score: scores.entrySet()) {
            sumOfScores += score.getValue();
        }
        return (float) sumOfScores / scores.size();
    }

    public void transferToNextCourse(){
        if (getAverageScore() >= 3 && course < 4) course+=1;
    }

    public String getName() {
        return lastName + " " + firstName;
    }

    public int getCourse() {
        return course;
    }
}
