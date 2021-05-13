package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleDTO {

    private String name;
    private int age;
    private float marks;
    private char grade;

    public String getName() {
        return name;
    }

    @JsonProperty("StudentName")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
