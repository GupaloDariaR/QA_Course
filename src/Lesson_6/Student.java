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
        this.course = course;
        this.scores = scores;
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
